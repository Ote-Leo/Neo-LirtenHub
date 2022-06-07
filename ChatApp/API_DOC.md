# **_LirtenHub Chat API Document File_**

</hr>

## Table of Contents

- [**_LirtenHub Chat API Document File_**](#lirtenhub-chat-api-document-file)
  - [Table of Contents](#table-of-contents)
  - [Chat App Routes](#chat-app-routes)
    - [1. Get Messages](#get-messages)
    - [2. Send Message](#send-message)
    - [3. Close Listener](#close-listener)
    - [4. Send Image](#send-image)
    - [5. Get Image](#get-image)

</hr>

## Chat App Routes

### 1. Get Messages

```json
    Create a `GET /getMessages/{usersID}` request

body {

}

    Success
{ 
    [
        {
            "messageID": 1,
            "messageText": "first message",
            "messageUser": "User1_Name",
            "messageTime": "2022-05-18T20:52:047Z"
        }
    ]
}

    Failure
{ 
    []
}
```

### 2. Send Message

```json
    Create a `POST /sendMessage/{usersID}` request

body {
    "messageID": 1,
    "messageText": "first message",
    "messageUser": "User1_Name"  
}

    Success
{ 
    messageID
}

    Failure
{ 
   
}
```

### 3.  Close Listener

```json
    Create a `GET /getMessages/close/{usersID}` request

body {

}

    Success
{ 
     1-2 listener closed
}

    Failure
{ 

}
```

### 4.  Send Image

```json
    Create a `POST /sendImage/{usersID}` request

body {
     {
        "messageID": 2,
        "messageText": "C:\\Users\\Documents\\Demo.png",
        "messageUser": "User1_Name"
    }
}

    Success
{ 

}

    Failure
{ 

}
```

### 5.  Get Image

```json
    Create a `GET /getImage/{ID}` request

body {

}

    Success
{ 
     
}

    Failure
{ 

}
```