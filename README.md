# Student Cookie Tracking System

## Overview

The Student Cookie Tracking System is a JavaFX application designed to help students track how many cookies they have. It provides an intuitive interface for managing student records, including their cookie counts, performance ratings for cookie storage, and their grade level, ranging from first grade to fifth grade.

## Features

- **User Authentication**: Secure login system with username and password.
- **Student Management**: Add, edit, and delete student records.
- **Data Visualization**: View student cookie data in a tabular format.
- **Profile Picture Management**: Upload and manage student profile pictures.
- **Data Export**: Export student cookie data in CSV format.

## Technical Stack

- **Language**: Java
- **UI Framework**: JavaFX
- **Database**: MySQL
- **Cloud Storage**: Azure Blob Storage
- **Additional Libraries**:
    - OpenCSV for CSV file handling
    - Azure Storage Blob for cloud storage operations

## Project Structure

The project is organized into several packages:

- `dao`: Data Access Objects for database operations and cloud storage.
- `model`: Contains the `Student` class representing a student's cookie record.
- `service`: Utility classes for logging and session management.
- `viewmodel`: Controllers for different views in the application.

## Key Components

### DbConnectivityClass

Handles all database operations, including CRUD operations for student cookie records.

### StorageUploader

Manages file uploads to Azure Blob Storage, particularly for student profile pictures.

### Student

Represents a student with attributes like name, grade level (first to fifth grade), cookie count, and performance rating for cookie storage.

### UserSession

Manages user sessions and credentials using the Singleton pattern.

### LoginController

Handles user authentication and navigation to the main interface.

### DB_GUI_Controller

Main controller for the student cookie tracking interface, handling most of the application's functionality.

### MainApplication

The entry point of the application, managing scene transitions and initial setup.

## Setup and Configuration

1. **Database Setup**:
    - Ensure MySQL is installed and running.
    - Update the database connection details in `DbConnectivityClass`.

2. **Azure Blob Storage**:
    - Set up an Azure Blob Storage account.
    - Update the connection string in `StorageUploader`.

3. **Dependencies**:
    - Ensure all required libraries are included in the project's classpath.

4. **Running the Application**:
    - Execute the `main` method in the `MainApplication` class.

## Usage

1. **Login**: Use registered credentials to log in.
2. **Main Interface**: Navigate through the tabs to manage student cookie data.
3. **Adding Students**: Fill in the form and click 'Add' to create new student records.
4. **Editing/Deleting**: Select a student from the table to edit or delete their information.
5. **Profile Pictures**: Upload or delete student profile pictures.
6. **Data Export**: Use the menu option to export data in CSV format.

## Security Considerations

- Passwords are hashed before storage.
- User sessions are managed securely.
- Input validation is implemented to prevent malicious data entry.

## Author

[Jeans17far](https://github.com/Jeans17far)
