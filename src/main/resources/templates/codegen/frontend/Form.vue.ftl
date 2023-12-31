<script setup>
import { computed, ref, toRefs } from 'vue'
import customAxios from '@/api/axios'
import { ${moduleLowerCase}${nameUpperCase} } from '@/api/${moduleLowerCase}/${moduleLowerCase}${nameUpperCase}Api'

const props = defineProps(['dialogVisible', 'title', 'formData'])
const emit = defineEmits(['closeDialog', 'refreshTableData'])

const { dialogVisible, title, formData } = toRefs(props)

const operationType = computed(() => {
  if (formData.value.id) {
    return 'update'
  } else {
    return 'create'
  }
})

const confirmLoading = ref(false)
const formRef = ref(null)
const formRules = {
<#list propertyList as property>
<#if !property.nullable>
    ${property.name}: [
    { required: true, message: '请输入${property.comment}', trigger: 'blur' },
  ],
</#if>
</#list>
}

const handleClose = () => {
  emit('closeDialog')
  formRef.value.resetFields()
}

const handleCancel = () => {
  handleClose()
}

const handleConfirm = () => {
  formRef.value.validate(valid => {
    if (valid) {
      confirmLoading.value = true
      if (operationType.value === 'create') {
        customAxios.post(${moduleLowerCase}${nameUpperCase}, formData.value)
            .then(response => {
              handleClose()
              emit('refreshTableData')
            })
            .catch(error => {
            })
            .finally(() => {
              confirmLoading.value = false
            })
      } else if (operationType.value === 'update') {
        customAxios.put(${moduleLowerCase}${nameUpperCase}, formData.value)
            .then(response => {
              handleClose()
              emit('refreshTableData')
            })
            .catch(error => {
            })
            .finally(() => {
              confirmLoading.value = false
            })
      }
    }
  })
}
</script>

<template>
  <el-dialog v-model="dialogVisible" :title="title" width="50%" :before-close="handleClose">
    <el-form class="two-column-form" ref="formRef" :model="formData" :rules="formRules" label-width="auto">
<#list propertyList as property>
        <el-table-column prop="${property.name}" label="${property.comment}" min-width="120" show-overflow-tooltip />
      <el-form-item label="${property.comment}" prop="${property.name}">
        <el-input placeholder="请输入${property.comment}" v-model="formData.${property.name}" />
      </el-form-item>
</#list>
    </el-form>
    <template #footer>
        <span>
            <el-button @click="handleCancel">取消</el-button>
            <el-button @click="handleConfirm" :loading="confirmLoading">确定</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<style scoped></style>