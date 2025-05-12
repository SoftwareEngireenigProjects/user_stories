## File Descriptions

### **Assets Module**
- **`Asset.java`**: Represents an individual asset with properties like ID, name, quantity, price, type, and purchase date.
- **`PortfolioManager.java`**: Provides methods to manage the portfolio, including adding, editing, viewing, and removing assets. Data is persisted in `assets.txt`.
- **`Main.java`**: A console-based interface for interacting with the asset management system.

### **User Bank Account Module**
- **`BankAccountConnector.java`**: Handles bank account validation, including card details and OTP verification.

### **User Sign-Up Module**
- **`SignUp.java`**: Manages user registration, including input validation and data storage.

### **User Login Module**
- **`login.java`**: Handles user authentication by validating credentials against stored data.

### **Financial Goals Module**
- **`FinancialGoals.java`**: Allows users to add, view, and manage financial goals. Goals are stored in `goals.txt`.

### **Zakat Calculator Module**
- **`Asset.java`**: Represents an asset with properties like name, type, amount, and duration.
- **`ZakatCalculatorApp.java`**: Calculates Zakat based on the user's portfolio of assets.

### **Database Files**
- **`users.txt`**: Stores user credentials (name, email, username, password).
- **`goals.txt`**: Stores financial goals (type, target amount, due date, saved amount).
- **`bank_accounts.csv`**: Stores bank account details (card number, holder name, expiry date, CVV).
- **`assets.txt`**: Stores asset details for the portfolio.



## How to Run

1. Clone the repository or download the source code.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).
3. Compile and run the desired module (e.g., `Main.java` for asset management or `ZakatCalculatorApp.java` for Zakat calculation).
4. Follow the on-screen instructions for each module.

---

## Features

- **Asset Management**: Add, view, edit, and remove assets.
- **User Authentication**: Sign up and log in with credential validation.
- **Financial Goals**: Track and manage financial goals.
- **Zakat Calculation**: Calculate Zakat based on asset types and durations.
- **Bank Account Validation**: Verify card details and OTP.

---

## Future Enhancements

- Add a graphical user interface (GUI) for better user experience.
- Implement advanced filtering and sorting for assets and goals.
- Integrate with external APIs for real-time data (e.g., stock prices, currency rates).

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.