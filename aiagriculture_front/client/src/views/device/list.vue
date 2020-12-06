<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item>
        <el-input v-model="searchObj.name" placeholder="设备名称" />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="searchObj.joinDateBegin"
          placeholder="开始时间"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item label="-">
        <el-date-picker
          v-model="searchObj.joinDateEnd"
          placeholder="结束时间"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="fetchData()"
        >查询</el-button
        >
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格 -->
    <el-table :data="list" border stripe>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="设备名称" width="80" />
      <el-table-column prop="type" label="设备型号" width="160" />
      <el-table-column prop="material" label="设备材质" />
      <el-table-column prop="specification" label="设备规格" />
      <el-table-column prop="gmt_create" label="创建时间" width="160" />
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            >修改</el-button
            >
          </router-link>
          <el-button
            size="mini"
            type="danger"
            @click="removeById(scope.row.id)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20]"
      style="padding: 30px 0; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total"
      @current-change="changeCurrentPage"
      @size-change="changePageSize"
    />
  </div>
</template>
<script>
import devApi from '@/api/device'

export default {
  data() {
    return {
      list: [], // device list
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 5, // 每页记录数
      searchObj: {} // 查询表单
    }
  },
  created() {
    this.fetchData()
    console.log(this.list.length)
  },

  methods: {
    // 请求数据
    fetchData() {
      devApi
        .pageList(this.page, this.limit, this.searchObj)
        .then((response) => {
          this.list = response.data.rows
          this.total = response.data.total
          console.log(this.list.length)
        })
      console.log(this.list.length)
    },
    changeCurrentPage(page) {
      // console.log('changeCurrentPage:' + page)
      this.page = page
      this.fetchData()
    },
    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },
    resetData() {
      this.searchObj = {}
      this.fetchData()
    },
    removeById(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return devApi.removeById(id)
        })
        .then((response) => {
          this.fetchData()
          this.$message({
            message: response.message,
            type: 'success'
          })
        })
        .catch((err) => {
          if (err === 'cancel') {
            this.$message({
              type: 'info',
              message: '取消'
            })
          }
        })
    }
  }
}
</script>
