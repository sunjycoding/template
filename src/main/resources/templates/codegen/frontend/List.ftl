<script setup>
import { ref, reactive, onMounted } from 'vue';
import ${moduleUpperCase}${nameUpperCase}Form from './${moduleUpperCase}${nameUpperCase}Form.vue';
import { confirmDeletion, presentSelection } from '@/utils/commonUtils'
import customAxios from '@/api/axios'
import { ${moduleLowerCase}${nameUpperCase}, ${moduleLowerCase}${nameUpperCase}Page } from '@/api/${moduleLowerCase}/${moduleLowerCase}${nameUpperCase}Api'

const criteriaData = reactive({})
const pageNumber = ref(1)
const pageSize = ref(10)
const totalElements = ref(0)
const tableLoading = ref(false)
const tableData = ref([])
const selectedTableData = ref([])
const selectedData = ref({})
const dialogVisible = ref(false)
const dialogTitle = ref('')

onMounted(() => {
  listTableData()
})

const listTableData = () => {
  tableLoading.value = true
  customAxios.get(${moduleLowerCase}${nameUpperCase}Page, {
    params: {
      pageNumber: pageNumber.value,
      pageSize: pageSize.value,
      ...criteriaData
    }
  })
      .then(response => {
        tableData.value = response.data.content
        totalElements.value = response.data.totalElements
      })
      .catch(error => {
      })
      .finally(() => {
        tableLoading.value = false
      });
}

const handleSizeChange = (value) => {
  pageSize.value = value
  pageNumber.value = 1
  listTableData()
}

const handleCurrentChange = (value) => {
  pageNumber.value = value
  listTableData()
}

const closeDialog = () => {
  dialogVisible.value = false
}

const handleCreate = () => {
  selectedData.value = {}
  dialogVisible.value = true
  dialogTitle.value = '新增'
}

const handleUpdate = (row) => {
  selectedData.value = JSON.parse(JSON.stringify(row))
  dialogVisible.value = true
  dialogTitle.value = '修改'
}

const handleDelete = (row) => {
  confirmDeletion()
      .then(() => {
        customAxios.delete(${moduleLowerCase}${nameUpperCase} + '/' + row.id)
            .then(response => {
              listTableData()
            })
            .catch(error => {
            })
      }).catch(() => {
  })

}

const handleBatchDelete = () => {
  if (selectedTableData.value.length) {
    const idList = selectedTableData.value.map(data => data.id)
    confirmDeletion().then(() => {
      customAxios.delete(${moduleLowerCase}${nameUpperCase}, {
        data: idList
      })
          .then(response => {
            listTableData()
          })
          .catch(error => {
          })
    })
  } else {
    presentSelection()
  }
}

const handleSelectionChange = (selection) => {
  selectedTableData.value = selection
}
</script>

<template>
  <el-container class="wrapper">
    <el-main>
      <${moduleUpperCase}${nameUpperCase}Form v-if="dialogVisible" :dialogVisible="dialogVisible" :title="dialogTitle"
                      :formData="selectedData" @closeDialog="closeDialog" @refreshTableData="listTableData" />
      <div class="table-criteria">
        <el-row>
          <el-form :inline="true" :model="criteriaData" label-width="auto">
            <el-form-item label="名称">
              <el-input v-model="criteriaData.name" placeholder="名称" />
            </el-form-item>
            <el-form-item>
              <el-button @click="listTableData">查询</el-button>
            </el-form-item>
          </el-form>
        </el-row>
        <el-row>
          <el-button @click="handleCreate">新增</el-button>
          <el-button @click="handleBatchDelete">删除</el-button>
        </el-row>
      </div>
      <el-divider />
      <div class="table-wrapper">
        <el-table v-loading="tableLoading" @selection-change="handleSelectionChange" :data="tableData" stripe>
          <el-table-column type="selection" fixed="left" width="55" />
          <el-table-column prop="name" label="名称" min-width="120" show-overflow-tooltip />
          <el-table-column align="center" prop="type" label="类型" min-width="120" show-overflow-tooltip />
          <el-table-column prop="path" label="路由" min-width="180" show-overflow-tooltip />
          <el-table-column align="center" prop="icon" label="图标" show-overflow-tooltip>
            <template #default="scope">
              <el-icon>
                <component :is="scope.row.icon" />
              </el-icon>
            </template>
          </el-table-column>
          <el-table-column prop="permissionTag" label="权限标识" min-width="120" show-overflow-tooltip />
          <el-table-column align="center" prop="orderNum" label="排序" min-width="80" show-overflow-tooltip />
          <el-table-column align="center" prop="enabled" label="启用状态" min-width="120" show-overflow-tooltip>
            <template #default="scope">
              <el-tag :type="scope.row.enabled ? '' : 'danger'">{{ scope.row.enabled ? '启用' : '禁用'
                }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="lastModifiedBy" label="修改人" min-width="120" show-overflow-tooltip />
          <el-table-column prop="lastModifiedDate" label="修改时间" min-width="120" show-overflow-tooltip />
          <el-table-column prop="createdBy" label="创建人" min-width="120" show-overflow-tooltip />
          <el-table-column prop="createdDate" label="创建时间" min-width="120" show-overflow-tooltip />
          <el-table-column align="center" fixed="right" label="操作" min-width="150">
            <template #default="scope">
              <el-row justify="space-around">
                <el-col :span="6">
                  <el-button class="btn-update-text" size="small" @click="handleUpdate(scope.row)"
                             text>修改</el-button>
                </el-col>
                <el-col :span="6">
                  <el-button class="btn-delete-text" size="small" @click="handleDelete(scope.row)"
                             text>删除</el-button>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination v-model:current-page="pageNumber" v-model:page-size="pageSize" :page-sizes="[10, 20, 50, 100]"
                     layout="sizes, prev, pager, next" :total="totalElements" @size-change="handleSizeChange"
                     @current-change="handleCurrentChange" background />
    </el-main>
  </el-container>
</template>

<style scoped></style>