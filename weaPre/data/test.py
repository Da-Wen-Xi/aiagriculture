import numpy

x = numpy.array([[1, 2, 3],
                 [4, 5, 6],
                 [7, 8, 9]])
x = numpy.delete(x, 0, axis=0)
print(x)
