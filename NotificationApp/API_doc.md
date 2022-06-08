# **_LirtenHub Notification API Document File_**

</hr>

## Table of Contents

- [**_LirtenHub Notification API Document File_**](#lirtenhub-notification-api-document-file)
  - [Table of Contents](#table-of-contents)
  - [Notification App Routes](#notification-app-routes)
    - [Get all user notifications](#get-all-user-notifications)

</hr>

## Notification App Routes

###  Get all user notifications


```json
    Create a `GET /notification/get/{user_id}` request

body {
    
}

    Success
{ 
    ["notification1", "notification2", "notification3"]
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist."
	"message2": "Opss! Transaction failed.  You are not logged in."
}
```


