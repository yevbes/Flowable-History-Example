# History configuration
To persist historic information it is necesary define history level with async history definition. This definitions can be configured using properties file. 

```sh
# flowable.process.servlet.path=/service # Process servlet path definition
flowable.history-level=full # History level definition
```
Async History has been introduced with Flowable 6.1.0 and allows historic data to be persisted asynchronously using a history job executor.

##### Following history levels can be configured:
- **none**: skips all history archiving. This is the most performant for runtime process execution, but no historical information will be available.
- **activity**: archives all process instances and activity instances. At the end of the process instance, the latest values of the top level process instance variables will be copied to historic variable instances. No details will be archived.
- **audit**: This is the default. It archives all process instances, activity instances, keeps variable values continuously in sync and all form properties that are submitted so that all user interaction through forms is traceable and can be audited.
- **full**: This is the highest level of history archiving and hence the slowest. This level stores all information as in the audit level plus all other possible details, mostly this are process variable updates.

## Rest API
#### History detail queries
Historic detail data can be fetched using following queries:
`[POST] {{baseUrl}}/query/historic-detail`
```json
{
    "start": "<integer>",
    "size": "<integer>",
    "sort": "<string>",
    "order": "<string>",
    "id": "<string>",
    "processInstanceId": "<string>",
    "executionId": "<string>",
    "activityInstanceId": "<string>",
    "taskId": "<string>",
    "selectOnlyFormProperties": "<boolean>",
    "selectOnlyVariableUpdates": "<boolean>"
}
```
`[GET] {{baseUrl}}/history/historic-detail`
|params|
|-|
|id - <string> |
|processInstanceId - <string> |
|executionId - <string> |
|activityInstanceId - <string> |
|taskId - <string> |
|selectOnlyFormProperties - <boolean>|
|selectOnlyVariableUpdates - <boolean>|

## Example
`[GET] {{baseUrl}}/history/historic-detail?processInstanceId=1c4b1e36-9b82-11ea-92fb-acde48001122`
```json
{
  "data": [
    {
      "id": "721984b9-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processInstanceUrl": "http://localhost:8090/process-api/history/historic-process-instances/1c4b1e36-9b82-11ea-92fb-acde48001122",
      "executionId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "activityInstanceId": "1c4cf2fd-9b82-11ea-92fb-acde48001122",
      "taskId": null,
      "taskUrl": null,
      "time": "2020-05-21T18:45:15.055+02:00",
      "detailType": "variableUpdate",
      "revision": 0,
      "variable": {
        "name": "globalVars",
        "type": "string",
        "value": "globalVar2",
        "scope": null
      },
      "propertyId": null,
      "propertyValue": null
    },
    {
      "id": "721984b7-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processInstanceUrl": "http://localhost:8090/process-api/history/historic-process-instances/1c4b1e36-9b82-11ea-92fb-acde48001122",
      "executionId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "activityInstanceId": "1c4cf2fd-9b82-11ea-92fb-acde48001122",
      "taskId": null,
      "taskUrl": null,
      "time": "2020-05-21T18:45:15.055+02:00",
      "detailType": "variableUpdate",
      "revision": 0,
      "variable": {
        "name": "localVars",
        "type": "string",
        "value": "localVars2",
        "scope": null
      },
      "propertyId": null,
      "propertyValue": null
    },
    {
      "id": "1c4b9368-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processInstanceUrl": "http://localhost:8090/process-api/history/historic-process-instances/1c4b1e36-9b82-11ea-92fb-acde48001122",
      "executionId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "activityInstanceId": null,
      "taskId": null,
      "taskUrl": null,
      "time": "2020-05-21T18:42:51.099+02:00",
      "detailType": "variableUpdate",
      "revision": 0,
      "variable": {
        "name": "initiator",
        "type": "string",
        "value": "admin",
        "scope": null
      },
      "propertyId": null,
      "propertyValue": null
    }
  ],
  "total": 3,
  "start": 0,
  "sort": "processInstanceId",
  "order": "asc",
  "size": 3
}
```

### History task logs queries
`[GET] {{baseUrl}}/history/historic-task-log-entries`

```json
{
  "data": [
    {
      "logNumber": 1,
      "type": "USER_TASK_CREATED",
      "taskId": "1c4fd92e-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:42:51.108+02:00",
      "userId": "admin",
      "data": null,
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 2,
      "type": "USER_TASK_IDENTITY_LINK_ADDED",
      "taskId": "1c4fd92e-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:42:51.131+02:00",
      "userId": "admin",
      "data": "{\"userId\":\"user1\",\"type\":\"candidate\"}",
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 3,
      "type": "USER_TASK_IDENTITY_LINK_ADDED",
      "taskId": "1c4fd92e-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:42:51.132+02:00",
      "userId": "admin",
      "data": "{\"userId\":\"user2\",\"type\":\"candidate\"}",
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 4,
      "type": "USER_TASK_COMPLETED",
      "taskId": "1c4fd92e-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:45:15.058+02:00",
      "userId": "admin",
      "data": "{}",
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 5,
      "type": "USER_TASK_CREATED",
      "taskId": "721c1cce-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:45:15.072+02:00",
      "userId": "admin",
      "data": null,
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 6,
      "type": "USER_TASK_IDENTITY_LINK_ADDED",
      "taskId": "721c1cce-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:45:15.072+02:00",
      "userId": "admin",
      "data": "{\"userId\":\"user1\",\"type\":\"candidate\"}",
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    },
    {
      "logNumber": 7,
      "type": "USER_TASK_IDENTITY_LINK_ADDED",
      "taskId": "721c1cce-9b82-11ea-92fb-acde48001122",
      "timeStamp": "2020-05-21T18:45:15.072+02:00",
      "userId": "admin",
      "data": "{\"userId\":\"user2\",\"type\":\"candidate\"}",
      "executionId": "1c4be18a-9b82-11ea-92fb-acde48001122",
      "processInstanceId": "1c4b1e36-9b82-11ea-92fb-acde48001122",
      "processDefinitionId": "bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002",
      "scopeId": null,
      "scopeDefinitionId": null,
      "subScopeId": null,
      "scopeType": null,
      "tenantId": ""
    }
  ],
  "total": 7,
  "start": 0,
  "sort": "logNumber",
  "order": "asc",
  "size": 7
}
```

#### Database definition table
`ACT_HI_VARINST`
```csv
id_,rev_,proc_inst_id_,execution_id_,task_id_,name_,var_type_,scope_id_,sub_scope_id_,scope_type_,bytearray_id_,double_,long_,text_,text2_,create_time_,last_updated_time_
1c4b6c57-9b82-11ea-92fb-acde48001122,0,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,initiator,string,,,,,,,admin,,2020-05-21 18:42:51.098000,2020-05-21 18:42:51.098000
72193696-9b82-11ea-92fb-acde48001122,0,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,localVars,string,,,,,,,localVars2,,2020-05-21 18:45:15.053000,2020-05-21 18:45:15.053000
721984b8-9b82-11ea-92fb-acde48001122,0,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,globalVars,string,,,,,,,globalVar2,,2020-05-21 18:45:15.055000,2020-05-21 18:45:15.055000
```

|id_                                 |rev_|proc_inst_id_                       |execution_id_                       |task_id_|name_     |var_type_|scope_id_|sub_scope_id_|scope_type_|bytearray_id_|double_|long_|text_     |text2_|create_time_              |last_updated_time_        |
|------------------------------------|----|------------------------------------|------------------------------------|--------|----------|---------|---------|-------------|-----------|-------------|-------|-----|----------|------|--------------------------|--------------------------|
|1c4b6c57-9b82-11ea-92fb-acde48001122|0   |1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |initiator |string   |         |             |           |             |       |     |admin     |      |2020-05-21 18:42:51.098000|2020-05-21 18:42:51.098000|
|72193696-9b82-11ea-92fb-acde48001122|0   |1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |localVars |string   |         |             |           |             |       |     |localVars2|      |2020-05-21 18:45:15.053000|2020-05-21 18:45:15.053000|
|721984b8-9b82-11ea-92fb-acde48001122|0   |1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |globalVars|string   |         |             |           |             |       |     |globalVar2|      |2020-05-21 18:45:15.055000|2020-05-21 18:45:15.055000|


`ACT_HI_DETAIL`
```csv
id_,type_,proc_inst_id_,execution_id_,task_id_,act_inst_id_,name_,var_type_,rev_,time_,bytearray_id_,double_,long_,text_,text2_
1c4b9368-9b82-11ea-92fb-acde48001122,VariableUpdate,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,,initiator,string,0,2020-05-21 18:42:51.099000,,,,admin,
721984b7-9b82-11ea-92fb-acde48001122,VariableUpdate,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,1c4cf2fd-9b82-11ea-92fb-acde48001122,localVars,string,0,2020-05-21 18:45:15.055000,,,,localVars2,
721984b9-9b82-11ea-92fb-acde48001122,VariableUpdate,1c4b1e36-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,,1c4cf2fd-9b82-11ea-92fb-acde48001122,globalVars,string,0,2020-05-21 18:45:15.055000,,,,globalVar2,
```

|id_                                 |type_         |proc_inst_id_                       |execution_id_                       |task_id_|act_inst_id_                        |name_     |var_type_|rev_|time_                     |bytearray_id_|double_|long_|text_     |text2_|
|------------------------------------|--------------|------------------------------------|------------------------------------|--------|------------------------------------|----------|---------|----|--------------------------|-------------|-------|-----|----------|------|
|1c4b9368-9b82-11ea-92fb-acde48001122|VariableUpdate|1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |                                    |initiator |string   |0   |2020-05-21 18:42:51.099000|             |       |     |admin     |      |
|721984b7-9b82-11ea-92fb-acde48001122|VariableUpdate|1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |1c4cf2fd-9b82-11ea-92fb-acde48001122|localVars |string   |0   |2020-05-21 18:45:15.055000|             |       |     |localVars2|      |
|721984b9-9b82-11ea-92fb-acde48001122|VariableUpdate|1c4b1e36-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|        |1c4cf2fd-9b82-11ea-92fb-acde48001122|globalVars|string   |0   |2020-05-21 18:45:15.055000|             |       |     |globalVar2|      |

`ACT_HI_TSK_LOG`
```csv
id_,type_,task_id_,time_stamp_,user_id_,data_,execution_id_,proc_inst_id_,proc_def_id_,scope_id_,scope_definition_id_,sub_scope_id_,scope_type_,tenant_id_
1,USER_TASK_CREATED,1c4fd92e-9b82-11ea-92fb-acde48001122,2020-05-21 18:42:51.108000,admin,,1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
2,USER_TASK_IDENTITY_LINK_ADDED,1c4fd92e-9b82-11ea-92fb-acde48001122,2020-05-21 18:42:51.131000,admin,"{""userId"":""user1"",""type"":""candidate""}",1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
3,USER_TASK_IDENTITY_LINK_ADDED,1c4fd92e-9b82-11ea-92fb-acde48001122,2020-05-21 18:42:51.132000,admin,"{""userId"":""user2"",""type"":""candidate""}",1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
4,USER_TASK_COMPLETED,1c4fd92e-9b82-11ea-92fb-acde48001122,2020-05-21 18:45:15.058000,admin,{},1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
5,USER_TASK_CREATED,721c1cce-9b82-11ea-92fb-acde48001122,2020-05-21 18:45:15.072000,admin,,1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
6,USER_TASK_IDENTITY_LINK_ADDED,721c1cce-9b82-11ea-92fb-acde48001122,2020-05-21 18:45:15.072000,admin,"{""userId"":""user1"",""type"":""candidate""}",1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
7,USER_TASK_IDENTITY_LINK_ADDED,721c1cce-9b82-11ea-92fb-acde48001122,2020-05-21 18:45:15.072000,admin,"{""userId"":""user2"",""type"":""candidate""}",1c4be18a-9b82-11ea-92fb-acde48001122,1c4b1e36-9b82-11ea-92fb-acde48001122,bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002,,,,,""
```

|id_                                 |type_         |task_id_                            |time_stamp_                         |user_id_|data_                               |execution_id_|proc_inst_id_|proc_def_id_|scope_id_                 |scope_definition_id_|sub_scope_id_|scope_type_|tenant_id_|
|------------------------------------|--------------|------------------------------------|------------------------------------|--------|------------------------------------|-------------|-------------|------------|--------------------------|--------------------|-------------|-----------|----------|
|1                                   |USER_TASK_CREATED|1c4fd92e-9b82-11ea-92fb-acde48001122|2020-05-21 18:42:51.108000          |admin   |                                    |1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|2                                   |USER_TASK_IDENTITY_LINK_ADDED|1c4fd92e-9b82-11ea-92fb-acde48001122|2020-05-21 18:42:51.131000          |admin   |{"userId":"user1","type":"candidate"}|1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|3                                   |USER_TASK_IDENTITY_LINK_ADDED|1c4fd92e-9b82-11ea-92fb-acde48001122|2020-05-21 18:42:51.132000          |admin   |{"userId":"user2","type":"candidate"}|1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|4                                   |USER_TASK_COMPLETED|1c4fd92e-9b82-11ea-92fb-acde48001122|2020-05-21 18:45:15.058000          |admin   |{}                                  |1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|5                                   |USER_TASK_CREATED|721c1cce-9b82-11ea-92fb-acde48001122|2020-05-21 18:45:15.072000          |admin   |                                    |1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|6                                   |USER_TASK_IDENTITY_LINK_ADDED|721c1cce-9b82-11ea-92fb-acde48001122|2020-05-21 18:45:15.072000          |admin   |{"userId":"user1","type":"candidate"}|1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
|7                                   |USER_TASK_IDENTITY_LINK_ADDED|721c1cce-9b82-11ea-92fb-acde48001122|2020-05-21 18:45:15.072000          |admin   |{"userId":"user2","type":"candidate"}|1c4be18a-9b82-11ea-92fb-acde48001122|1c4b1e36-9b82-11ea-92fb-acde48001122|bpm:1:7d754d5b-9b80-11ea-8ed6-0242c0a80002|                          |                    |             |           |          |
