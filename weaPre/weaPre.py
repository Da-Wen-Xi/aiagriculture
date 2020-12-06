import numpy as np
import pandas as pd
import torch
from sklearn.preprocessing import StandardScaler

txt = np.loadtxt('./data/data.txt')
txtDF = pd.DataFrame(txt)
txtDF.to_csv('data.csv', index=False)

path = 'data.csv'
data = pd.read_csv(path)
data = np.array(data)
# deal data
data = np.delete(data, 0, axis=0)
data = np.delete(data, 0, axis=1)
data = np.delete(data, 0, axis=1)

# 前50年作为训练数据
data = np.delete(data, np.s_[-240:-1], axis=0)
# 取最后20年作为测试数据
data_test = data[-241:-1]

[rows, cols] = data.shape
print(data.shape)
for i in range(rows):
    for j in range(cols):
        if i >= rows:
            break
        if data[i, j] == 999999:
            data = np.delete(data, i, axis=0)
            [rows, cols] = data.shape
actual_y = data[:, 2]
input_data = StandardScaler().fit_transform(data)

# 构建网络模型
input_size = input_data.shape[1]
hidden_size = 256
output_size = 1
batch_size = 16
pre_nn = torch.nn.Sequential(
    torch.nn.Linear(input_size, hidden_size),
    torch.nn.Linear(hidden_size, hidden_size),
    torch.nn.ReLU(),
    torch.nn.Linear(hidden_size, output_size)
)
# 损失函数
cost = torch.nn.MSELoss(reduction='mean')
# 优化器
optimizer = torch.optim.Adam(pre_nn.parameters(), lr=0.001)

# 训练网络
losses = []
for i in range(1000):
    batch_loss = []
    for start in range(0, len(input_data), batch_size):
        end = start + batch_size if batch_size + start < len(input_data) else len(input_data)
    x_train = torch.tensor(input_data[start:end], dtype=torch.float, requires_grad=True)
    y_train = torch.tensor(actual_y[start:end], dtype=torch.float, requires_grad=True)
    prediction = pre_nn(x_train)
    loss = cost(prediction, y_train)
    optimizer.zero_grad()
    loss.backward(retain_graph=True)
    optimizer.step()
    batch_loss.append(loss.data.numpy())
    if i % 100 == 0:
        losses.append(np.mean(batch_loss))
        print(i, np.mean(batch_loss), batch_loss)

# 测试预测结果
test_x = torch.tensor(data_test, dtype=torch.float)
print(test_x.shape)
predictions = pre_nn(test_x).detach().numpy()
print(predictions)
# test_predict = pre_nn(test_x).data.numpy()

