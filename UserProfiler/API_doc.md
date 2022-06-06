# **_LirtenHub User Profiler API Document File_**

</hr>

## Table of Contents

- [**_LirtenHub User Profiler API Document File_**](#lirtenhub-user-profiler-api-document-file)
  - [Table of Contents](#table-of-contents)
  - [User Profiler App Routes](#user-profiler-app-routes)
    - [1. Edit Profile](#1-edit-profile)
      - [----- Edit First Name](#------edit-first-name)
      - [----- Edit Last Name](#------edit-last-name)
      - [----- Edit Password](#------edit-password)
    - [2. Add bio](#2-add-bio)
    - [3. Add multiple favorite Coding languages](#3-add-multiple-favorite-coding-languages)
    - [4.  Block users](#4--block-users)
    - [5.  Report users](#5--report-users)
    - [6.  Attach link to gitHub account](#6--attach-link-to-github-account)
      - [----- Add github account](#------add-github-account)
      - [----- Remove github account](#------remove-github-account)
    - [7.  Choose set of interests](#7--choose-set-of-interests)
      - [----- Add interest](#------add-interest)
      - [----- Remove interest](#------remove-interest)
    - [8.  Choose preferences for projects](#8--choose-preferences-for-projects)
      - [----- Add project preference](#------add-project-preference)
      - [----- Remove project preference](#------remove-project-preference)
    - [9.  Moderators see reported users](#9--moderators-see-reported-users)

</hr>

## User Profiler App Routes

### 1. Edit Profile

#### ----- Edit First Name

```json
    Create a `PUT /api/session/usr/profile/editFirstName/{User_ID}` request

body {
    "first_name": "Hesham"
}

    Success
{ 
    "message": "First name updated successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

#### ----- Edit Last Name

```json
    Create a `PUT /api/session/usr/profile/editLastName/{User_ID}` request

body {
    "last_name": "Ghonim"
}

    Success
{ 
    "message": "Last name updated successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

#### ----- Edit Password

```json
    Create a `PUT /api/session/usr/profile/editPassword/{User_ID}` request

body {
    "password": "AshrafPass"    
}

    Success
{ 
    "message": "Password updated successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

### 2. Add bio

```json
    Create a `POST /api/user/bio/add_bio` request

body {
    "user_id": 1,
    "text_section": "Hi, I am Mohamed Ashraf."
}

    Success
{ 
    "message": "Bio updated successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

### 3. Add multiple favorite Coding languages

```json
    Create a `POST /api/session/usr/codingLanguages/{user_id}` request

body {
    "coding_language": "Java"   
}

    Success
{ 
    "message": "Java added successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
	"message2": "Opss! Transaction failed. Java already found!"
}
```

### 4.  Block users

```json
    Create a `POST /api/user/block_user` request

body {
    "user_id": 2,
    "blocked_id": 1
}

    Success
{ 
    "message": "Blocked successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist",
	"message2": "Opss! Transaction failed. BLOCKED USER does not exist",
    "message3": "Opss! Transaction failed. Already blocked!"

}
```

### 5.  Report users

```json
    Create a `POST /api/session/usr/report` request

body {
    "user_id": 1,
    "reported_id": 2,
    "report_description": "Violence"
}

    Success
{ 
    "message": "Reported successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist",
	"message2": "Opss! Transaction failed. REPORTED USER does not exist",
    "message3": "Opss! Transaction failed. Already reported!"
}
```

### 6.  Attach link to gitHub account

#### ----- Add github account

```json
    Create a `POST /api/session/attach_github_link/{user_id}` request

body {
    "github_link":"github.com/ashraf"
}

    Success
{ 
    "message": "Github link added successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

#### ----- Remove github account

```json
    Create a `DELETE /api/session/delete_github_link/{user_id}` request

body {
    
}

    Success
{ 
    "message": "Github link deleted successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

### 7.  Choose set of interests

#### ----- Add interest

```json
    Create a `POST /api/session/usr/add_interest/{user_id}` request

body {
    "interest": "PS"
}

    Success
{ 
    "message": "PS added successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
	"message2": "Opss! Transaction failed. PS already found!"
}
```

#### ----- Remove interest

```json
    Create a `DELETE /api/session/usr/delete_interest/{user_id}` request

body {
    "interest": "PS"

}

    Success
{ 
    "message": "PS deleted successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

### 8.  Choose preferences for projects

#### ----- Add project preference

```json
    Create a `POST /api/session/usr/project_selection/add_preference/{user_id}` request

body {
	"preference": "ML"
}

    Success
{ 
    "message": "ML added successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
	"message2": "Opss! Transaction failed. ML already found!"
}
```

#### ----- Remove project preference

```json
    Create a `DELETE /api/session/usr/project_selection/delete_preference/{user_id}` request

body {
    "preference": "ML"
}

    Success
{ 
    "message": "ML deleted successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. USER does not exist."
}
```

### 9.  Moderators see reported users

```json
    Create a `GET /api/session/usr/project_selection/get_report` request

body {
    
}

    Success
{ 
    [
        "User Abozied Ghonim reported Mohamed Ehab for Violence",
        "User Ashraf Askora reported Ali Halim for False Information"
    ]
}

    Failure
{ 
    
}
```