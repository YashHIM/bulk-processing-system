# Bulk CSV Processing System

## Overview

This project is a Spring Boot backend application that processes large CSV files. Each row in the file contains item data linked to a group. The system reads the file, validates the data, removes duplicates, and stores it in a relational database.

## Features

* Upload CSV file through API
* Background processing using async execution
* Validation of input data
* Duplicate handling:

  * Groups are unique by group_id
  * Items are unique by group_id + item_name
* Batch insertion for better performance
* Job status tracking

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Apache Commons CSV

## API Endpoints

### Upload File

POST /api/files/upload

Upload a CSV file using multipart/form-data.

Response:
Returns a jobId which can be used to check processing status.

---

### Check Job Status

GET /api/files/status/{jobId}

Response:
Returns processing status along with total, processed, and failed records.

---

## CSV Format

The CSV file must have the following header:

record_id,group_id,group_name,item_name,quantity,price,created_date

## Notes

* Duplicate groups are not created again
* Duplicate items within the same group are ignored
* File processing happens in background, so upload API returns immediately
