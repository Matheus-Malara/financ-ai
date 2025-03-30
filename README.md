# FinancAI Backend

Spring Boot backend for **FinancAI**, a personal project that uses AI to analyze U.S. stocks based on fundamental indicators provided by [Alpha Vantage](https://www.alphavantage.co) API. Built entirely in Java 21 with a focus on learning clean architecture and real-world API integration.

---

## 📌 Description

This application receives a stock ticker (e.g. `WOLF`), fetches financial fundamentals from [Alpha Vantage](https://www.alphavantage.co), formats the data, and sends it to [OpenAI](https://openai.com) to generate an investment summary and conclusion.

It returns both:
- The **raw indicators** (P/E ratio, ROE, etc.)
- A **summary and conclusion** written by ChatGPT

---

## 🚀 Technologies Used

- Java 21
- Spring Boot 3.4
- WebFlux (non-blocking HTTP client)
- OpenAI API (ChatGPT)
- Alpha Vantage API
- Jackson (JSON serialization)
- JUnit 5 & Mockito
- Lombok
- Gradle

---

## 🔧 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/matheus-malara/financ-ai.git
cd financ-ai
```

### 2. Add your API keys

Create a `src/main/resources/application.properties` file:

```properties
openai.api.key=YOUR_OPENAI_API_KEY
openai.model=gpt-3.5-turbo
alphavantage.api.key=YOUR_ALPHA_VANTAGE_API_KEY
```

### 3. Run the project

```bash
./gradlew bootRun
```

Or run `FinancAiApplication.java` via IntelliJ.

### 4. Test the API

```
GET http://localhost:8080/analysis/WOLF
```

---

## ✅ Testing

The project includes a unit test using:

- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/) for mocking dependencies

### Running tests

```bash
./gradlew test
```

The main focus of the tests is to ensure the logic in:
- `StockAnalysisService` works as expected when handling external APIs
- AI response formatting behaves correctly

Test classes are located under:

```
src/test/java/com/analysis/financ_ai.service
```

---

## 📦 Sample Response

```json
{
  "indicators": {
    "PERatio": "15.2",
    "MarketCapitalization": "1.3T"
    "..."  
  },
  "ai_analysis": {
    "summary": "The company shows strong profitability and stable growth.",
    "conclusion": "It's a promising long-term investment."
  }
}
```

---

## 🧠 Learning Goals

This project was built as a personal learning journey to explore and practice:

- API integration with external services (Alpha Vantage and OpenAI)
- Prompt engineering for investment analysis using GPT
- Service and controller layer architecture with clean separation of concerns
- DTO and model design using Jackson and Lombok
- Reactive programming using WebClient (non-blocking I/O)
- Writing unit tests using **JUnit 5** and mocking with **Mockito**
- Exception handling and formatting for a RESTful API

---

## 📂 Project Structure

```
com.analysis.financ_ai
├── controller          // REST API controller
├── exception           // Global exception handler
├── model               // DTOs: StockOverview, AiAnalysis, etc.
├── openai              // Integration with OpenAI API
├── service             // Business logic and external API calls
└── FinancAiApplication // Spring Boot main class
```

---

## 🛡️ Security Note

The OpenAI and Alpha Vantage API keys are required to run the project.  
**Never expose them publicly.** Add `.env`, `application.properties`, and other sensitive files to your `.gitignore`.

---

## 🌐 Related Projects

- [FinancAI Frontend (React + Vite)](https://github.com/matheus-malara/financ-ai-frontend)
