# OpenWeather API Automation (Rest Assured + TestNG)

Covers the assignment criteria using a layered framework:
- **com.api.base**: `BaseService`, `Config`
- **com.api.services**: `WeatherService`
- **com.api.models.weather**: POJOs for response mapping
- **com.api.tests**: 7 classes matching the test criteria
- **ExtentReports** (light theme) written to `reports/ExtentReport.html`

## How to Run
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```
Or in Eclipse: right-click `testng.xml` → *Run As → TestNG Suite*.

## Configuration
- API key is hardcoded in `Config.java` (`API_KEY`). You can change it there.
- One Call 3.0 tests are **disabled by default**. To enable, set `ENABLE_ONECALL = true` in `Config.java`
  (You need a One Call 3.0 subscription on your key).

## What’s Covered
1. **API Key Authentication** – `AuthKeyTests`
2. **Endpoint Response Codes** – `StatusCodeTests`
3. **Response Structure & Schema** – `SchemaValidationTests`
4. **Parameter Handling & Validation** – `ParamHandlingTests`
5. **Query Parameter Combinations** – `QueryCombinationTests`
6. **Performance & Response Time** – `PerformanceTests`
7. **Data Accuracy & Consistency** – `DataAccuracyTests`

## Notes
- Free-tier tests use `/data/2.5/weather` and should pass with a free API key.
- One Call 3.0 tests use `/data/3.0/onecall` and require paid access.
