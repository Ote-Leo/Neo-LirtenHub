# **_Neo-LirtenHub Chat API Document File_**

</hr>

## Table of Contents

- [**_Neo-LirtenHub Chat API Document File_**](#neo-lirtenhub-chat-api-document-file)
  - [Table of Contents](#table-of-contents)
  - [Chat App Routes](#chat-app-routes)
    - [1. Get Messages](#1-get-messages)
      - [Get Messages: Success](#get-messages-success)
      - [Get Messages: Failure](#get-messages-failure)
    - [2. Send Message](#2-send-message)
      - [Send Message: Success](#send-message-success)
      - [Send Messages: Failure](#send-messages-failure)
    - [3. Close Listener](#3-close-listener)
      - [Close Listener: Success](#close-listener-success)
      - [Close Listener: Failure](#close-listener-failure)

</hr>

## Chat App Routes

### 1. Get Messages

Create a `GET /getMessages/{usersID}` request

```json
{}
```

#### Get Messages: Success

```json
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
```

#### Get Messages: Failure

```json
{
    []
}
```

### 2. Send Message

Create a `POST /sendMessage/{usersID}` request

```json
{
  "messageID": 1,
  "messageText": "first message",
  "messageUser": "User1_Name"
}
```

#### Send Message: Success

```json
{
    messageID
}
```

#### Send Messages: Failure

```json
{}
```

### 3. Close Listener

Create a `GET /getMessages/close/{usersID}` request

```json
{}
```

#### Close Listener: Success

```json
{
  // 1-2 listener closed
}
```

#### Close Listener: Failure

```json
{}
```
