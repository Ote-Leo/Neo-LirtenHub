# **_LirtenHub Project Manager API Document File_**

</hr>

## Table of Contents

- [**_LirtenHub Project Manager API Document File_**](#lirtenhub-project-manager-api-document-file)
  - [Table of Contents](#table-of-contents)
  - [Project Manager App Routes](#project-manager-app-routes)
    - [1. Project](#1-project)
      - [----- Create project](#------create-project)
      - [----- Edit Project](#------edit-project)
      - [----- Delete Project](#------delete-project)
    - [2. Project Applicant](#2-project-applicant)
      - [----- Apply on a project](#------apply-on-a-project)
      - [----- Remove a project applicant](#------remove-a-project-applicant)
      - [----- Accept a project applicant](#------accept-a-project-applicant)

</hr>

## Project Manager App Routes

### 1. Project

#### ----- Create project

```json
    Create a `POST api/user/project_handler/create_project` request

body {
    "owner_id": 1,
    "project_title": "Scalable",
    "project_description": "Lirten-Hub",
    "start_date": "2022-06-16",
    "end_date": "2022-06-20",
    "tasks": ["MS1", "MS2","MS3"],
    "price": "20.0",
    "project_programming_languages": ["Java", "Python"],
    "project_preference_tags": ["ML", "Web"],
    "status": "started"
}

    Success
{ 
    "message": "Project added successfully!"
}

    Failure
{ 
    "message": "Opss! Transaction failed. OWNER does not exist."
}
```

#### ----- Edit Project

```json
    Create a `PUT api/user/project_handler/update_project` request

body {
    "project_id": 1,
    "project_title": "ITPM",
    "start_date": "2022-06-10",
    "tasks": ["MS4"]
}

    Success
{ 
    "message": "Project updated successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. Please specify the PROJECT ID.",
    "message2": "Opss! Transaction failed. PROJECT does not exist."

}
```

#### ----- Delete Project

```json
    Create a `DELETE api/user/project_handler/delete_project` request

body {
    "project_id": 1
}

    Success
{ 
    "message": "Project deleted successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. Please specify the PROJECT ID.",
    "message2": "Opss! Transaction failed. PROJECT does not exist."
}
```

### 2. Project Applicant

#### ----- Apply on a project

```json
    Create a `POST api/user/applicant_handler/add_applicant` request

body {
    "user_id": 100,
    "project_id": 3
}

    Success
{ 
    "message": "Project Applicant added successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
    "message2": "Opss! Transaction failed. PROJECT does not exist."
}
```

#### ----- Remove a project applicant

```json
    Create a `DELETE api/user/applicant_handler/remove_applicant` request

body {
    "user_id": 100,
    "project_id": 3
}

    Success
{ 
    "message": "Project Applicant deleted successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
    "message2": "Opss! Transaction failed. PROJECT does not exist.",
    "message3": "Opss! Transaction failed. There is no such project applicant."
}
```

#### ----- Accept a project applicant

```json
    Create a `PUT api/user/applicant_handler/accept_applicant` request

body {
    "user_id":100,
    "project_id":3,
    "owner_id": 1
}

    Success
{ 
    "message": "Project Applicant added successfully!"
}

    Failure
{ 
    "message1": "Opss! Transaction failed. USER does not exist.",
    "message2": "Opss! Transaction failed. PROJECT does not exist."
}
```

