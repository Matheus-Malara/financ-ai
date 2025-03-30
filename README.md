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

## 📦 Sample Response

```json
{
  "indicators": {
    "PERatio": "15.2",
    "MarketCapitalization": "1.3T"
    ...  
  },
  "ai_analysis": {
    "summary": "The company shows strong profitability and stable growth.",
    "conclusion": "It's a promising long-term investment."
  }
}
```

---

## 🧠 Learning Goals

This project was built as a learning journey to explore:
- API integration with external services
- Prompt engineering for investment analysis
- Service and controller layer architecture
- Clean DTO and model design
- Use of WebClient (non-blocking I/O)

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
