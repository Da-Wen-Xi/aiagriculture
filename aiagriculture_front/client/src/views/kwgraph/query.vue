<template>
  <div>
    <div class="app-container">
      <el-form :inline="true">
        <el-form-item>
          <el-input placeholder="实体名称" />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="fetchData()"
          >查询</el-button
          >
          <el-button type="default">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div id="container"/>
  </div>
</template>
<script>
function refreshDragedNodePosition(e) {
  const model = e.item.get('model')
  model.fx = e.x
  model.fy = e.y
}
import kgApi from '../../api/kwgraph'
import G6 from '@antv/g6'
export default {
  data() {
    return {
      list: [],
      nodes: [],
      edges: []
    }
  },
  mounted() {
    this.initG6()
  },
  methods: {
    // 数组对象去重
    unique(array) {
      const result = {}
      const finalResult = []
      for (let i = 0; i < array.length; i++) {
        result[array[i].id] = array[i]
      }
      for (const item in result) {
        finalResult.push(result[item])
      }
      return finalResult
    },
    async fetchData() {
      await kgApi
        .queryKg(this.name)
        .then((response) => {
          this.list = response.data.rows
        })
    },
    // 处理数据
    async dealData() {
      await this.fetchData()
      for (let i = 0; i < this.list.length; i++) {
        const eobj = {}
        let nobj = {}
        nobj.label = this.list[i].startNode.title
        nobj.id = this.list[i].startNode.id.toString()
        this.nodes.push(nobj)
        nobj = {}
        nobj.id = this.list[i].endNode.id.toString()
        nobj.label = this.list[i].endNode.title
        this.nodes.push(nobj)
        eobj.source = this.list[i].startNode.id.toString()
        eobj.target = this.list[i].endNode.id.toString()
        eobj.label = this.list[i].type
        this.edges.push(eobj)
      }
    },
    async initG6() {
      // TODO 数据节点去重，目前是G6进行自动处理
      await this.dealData()
      const nodes = this.nodes
      const edges = this.edges
      const width = document.getElementById('container').scrollWidth || 500
      const height = document.getElementById('container').scrollHeight || 500
      const graph = new G6.Graph({
        container: 'container',
        width,
        height,
        layout: {
          type: 'force',
          preventOverlap: true,
          linkDistance: 200
        },
        defaultNode: {
          color: '#5B8FF9',
          style: {
            lineWidth: 2,
            fill: '#C6E5FF'
          }
        },
        defaultEdge: {
          size: 1,
          color: '#e2e2e2'
        }
      })
      graph.data({ nodes, edges })
      graph.render()
      const forceLayout = graph.get('layoutController').layoutMethod
      graph.on('node:dragstart', function(e) {
        graph.layout()
        refreshDragedNodePosition(e)
      })
      graph.on('node:drag', function(e) {
        forceLayout.execute()
        refreshDragedNodePosition(e)
      })
      graph.on('node:dragend', function(e) {
        forceLayout.execute()
        refreshDragedNodePosition(e)
      })
    }
  }
}

</script>

