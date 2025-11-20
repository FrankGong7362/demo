# 学习资料管理后端接口需求文档（更新版）

## 1. 接口概览

### 1.1 基础信息
- **基础路径**: `/materials`
- **请求方式**: POST
- **数据格式**: JSON
- **认证方式**: JWT Token

### 1.2 接口列表
| 接口名称 | 请求地址 | 功能描述 |
|---------|---------|---------|
| 获取学习资料列表 | `/materials/list` | 分页获取学习资料列表 |
| 下载学习资料 | `/materials/download` | 下载指定学习资料 |
| 获取学习资料详情 | `/materials/detail` | 获取学习资料详细信息 |
| 获取统计信息 | `/materials/statistics` | 获取学习资料统计信息 |
| 获取学科分类列表 | `/materials/subjectCategories` | 获取动态学科分类选项 |
| 获取资料类型列表 | `/materials/materialTypes` | 获取动态资料类型选项 |

## 2. 详细接口规范

### 2.1 获取学习资料列表 `/materials/list`

#### 请求参数
```json
{
  "page": 1,
  "pageSize": 12,
  "subject": "computer_science",
  "type": "courseware",
  "keyword": "算法",
  "sortBy": "latest"
}
```


| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| `page` | Integer | 否 | 页码，默认1 |
| [pageSize](file://C:\Users\GMT\vsCodeProjects\vue_demo\src\views\materials\All.vue#L208-L208) | Integer | 否 | 每页数量，默认12 |
| [subject](file://C:\Users\GMT\vsCodeProjects\vue_demo\src\views\materials\All.vue#L185-L185) | String | 否 | 学科分类 |
| [type](file://C:\Users\GMT\vsCodeProjects\vue_demo\src\views\materials\All.vue#L186-L186) | String | 否 | 资料类型 |
| `keyword` | String | 否 | 搜索关键词 |
| `sortBy` | String | 否 | 排序方式: latest, popular, recommended |

#### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "title": "数据结构与算法",
        "description": "详细讲解各种数据结构和经典算法",
        "subject": "computer_science",
        "type": "courseware",
        "uploader": "张教授",
        "uploadTime": "2024-01-15T10:30:00",
        "downloadCount": 1234,
        "rating": 4.5,
        "reviewCount": 89
      }
    ],
    "total": 156,
    "page": 1,
    "pageSize": 12
  }
}
```


### 2.2 下载学习资料 `/materials/download`

#### 请求参数
```json
{
  "id": 1
}
```


| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| [id](file://C:\Users\GMT\vsCodeProjects\vue_demo\src\views\materials\All.vue#L182-L182) | Integer | 是 | 学习资料ID |

#### 响应格式
- **响应类型**: `application/octet-stream`
- **响应内容**: 文件二进制流

### 2.3 获取学习资料详情 `/materials/detail`

#### 请求参数
```json
{
  "id": 1
}
```


| 参数名 | 类型 | 必填 | 说明 |
|-------|------|------|------|
| [id](file://C:\Users\GMT\vsCodeProjects\vue_demo\src\views\materials\All.vue#L182-L182) | Integer | 是 | 学习资料ID |

#### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "title": "数据结构与算法",
    "description": "详细讲解各种数据结构和经典算法，附带练习题",
    "subject": "computer_science",
    "type": "courseware",
    "uploader": "张教授",
    "uploadTime": "2024-01-15T10:30:00",
    "downloadCount": 1234,
    "rating": 4.5,
    "reviewCount": 89,
    "fileSize": "2.5MB",
    "filePath": "/uploads/materials/2024/01/data_structure.pdf"
  }
}
```


### 2.4 获取统计信息 `/materials/statistics`

#### 请求参数
无

#### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "totalResources": 156,
    "subjectCount": 12,
    "todayDownloads": 42,
    "avgRating": 4.3
  }
}
```


### 2.5 获取学科分类列表 `/materials/subject-categories`

#### 请求参数
无

#### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "label": "计算机科学",
      "value": "computer_science"
    },
    {
      "label": "数学",
      "value": "mathematics"
    },
    {
      "label": "物理学",
      "value": "physics"
    }
  ]
}
```


### 2.6 获取资料类型列表 `/materials/material-types`

#### 请求参数
无

#### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "label": "课件",
      "value": "courseware"
    },
    {
      "label": "习题集",
      "value": "exercises"
    },
    {
      "label": "实验指导",
      "value": "lab_guide"
    }
  ]
}
```


## 3. 枚举值定义

学科分类和资料类型现在通过动态接口获取，不再使用固定枚举值。

## 4. 错误响应格式

```json
{
  "code": 400,
  "message": "参数错误",
  "data": null
}
```


## 5. 权限要求

- 所有接口需要用户登录状态
- 普通用户可查看和下载资料
- 管理员用户可进行资料管理操作

## 6. 前端集成说明

前端需要在页面加载时调用以下接口获取动态选项：
1. `getSubjectCategories` - 获取学科分类下拉选项
2. `getMaterialTypes` - 获取资料类型下拉选项

这样可以实现学科分类和资料类型的后台配置化管理。