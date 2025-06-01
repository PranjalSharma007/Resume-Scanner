# 📄 Resume Scanner

> *Because manually checking if your resume matches a job description is so 2010...*

## What is this Project all about?

Resume Scanner is a Java application that analyzes your resume against job descriptions to see if you're a good match. It extracts skills from both documents, calculates match percentages, and generates a detailed report with recommendations. Now you can find out why you're getting ghosted by recruiters before you even apply! 

## Why I chose Java?

I chose Java for this project because:
1. **Cross-platform compatibility** - Works on Windows, Mac, Linux (recruiters use all kinds of systems!)
2. **Rich UI libraries** - Swing makes it easy to create a simple but functional interface
3. **PDF processing libraries** - Apache PDFBox handles the complex task of extracting text from PDFs
4. **Object-oriented design** - Perfect for modeling the different components of resume analysis
5. **Learning opportunity** - Great way to practice Java's design patterns in a real-world application

## Project Structure

| Class | Description |
|-------|-------------|
| `ResumeScannerApp` | Entry point of the application that launches the UI |
| `MainUI` | The graphical user interface for uploading resumes and displaying results |
| `MainController` | Orchestrates the resume scanning process and database operations |
| `ResumeParser` | Extracts text and information from PDF resumes using Apache PDFBox |
| `KeywordAnalyzer` | Identifies and matches keywords between resumes and job descriptions |
| `ReportGenerator` | Creates formatted reports based on the analysis results |
| `DBConnection` | Manages database connections for storing report data |
| `ResumeData` | Data model class that stores parsed resume information and match statistics |
| `Report` | Simple model class for storing report content and file location |

## Workflow

1. **Input**: User selects a resume PDF file and enters a username (required)
2. **Processing**:
   - Resume is parsed using Apache PDFBox to extract text
   - Job description is loaded from a predefined file
   - Skills are extracted from both documents
   - Matching is performed for both required and preferred skills
   - A weighted score is calculated (75% required skills, 25% preferred skills)
   - Report is generated with detailed matching information
3. **Output**:
   - A formatted report is displayed in the application
   - Report is saved to disk for future reference
   - Basic information is stored in a database

## Report Generation Logic

The application generates a detailed report showing:
- Candidate details (name, email extracted from resume)
- Job description details (title, company, location)
- Required skills match percentage with lists of matched and missing skills
- Preferred skills match percentage with lists of matched and missing skills
- Weighted match score combining both required and preferred skills
- Final recommendation based on the weighted score:
  - ≥80%: STRONG MATCH - RECOMMENDED
  - ≥60%: GOOD MATCH - WORTH CONSIDERING
  - ≥40%: MODERATE MATCH - REVIEW FURTHER
  - <40%: LOW MATCH - NOT RECOMMENDED


## How to Run the Project

1. **Navigate to the project directory**:
   ```bash
   cd path/to/ResumeScanner
   javac -cp ".:lib/" -d bin src/Model/.java src/Controller/.java src/View/.java ResumeScannerApp.java
   java -cp ".:bin:lib/*" ResumeScannerApp
   ```
2. **Run the build script**:
   ```bash
   ./build.sh
   ```
   Make sure  build.sh has executable permissions. If not, run:
   ```bash
   chmod +x build.sh
   ```
3. **Setting up Database**:
   PostgreSQL Version
   ```bash
   CREATE TABLE reports (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    filename VARCHAR(255),
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```
    MySQL Version
    ```bash
    CREATE TABLE reports (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) DEFAULT NULL,
    filename VARCHAR(255) DEFAULT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id))
   AUTO_INCREMENT = 1;
   ```

## 📊 View Saved Reports

To verify that user reports are being correctly saved in your database, you can run the following SQL command in your database client or terminal:

```sql
SELECT * FROM reports;
```

## Changes Required
🧩 DBConnection.java Changes
Make sure to modify the connection credentials to match your PostgreSQL server.

```java
// Example for PostgreSQL
String url = "jdbc:postgresql://<host>:<port>/<database>";
String user = "<your_username>";
String password = "<your_password>";
```


## 📚 Dependencies

Ensure these .jar libraries are placed in your lib/ directory:

pdfbox-2.0.27.jar
fontbox-2.0.27.jar
commons-logging-1.2.jar
pdfbox-app-2.0.27.jar
postgresql-42.2.27.jar (or the latest PostgreSQL JDBC driver)


## Future Work

-  **Custom job descriptions**: Allow users to upload or paste job descriptions instead of using a predefined file
-  **More file formats**: Support for Word documents, plain text, and HTML resumes
-  **Detailed skill analysis**: Add visualization of skill matches with strength indicators
-  **ATS simulation**: Add features to simulate how ATS systems might score the resume
-  **Job recommendation**: Suggest which skills to add to improve match percentages
-  **Cloud storage**: Store reports online for access from multiple devices
-  **Browser extension**: Create a browser extension to analyze job postings on popular job sites
-  **API service**: Create a RESTful API to allow other applications to use the scanning functionality