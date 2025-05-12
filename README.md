## User stories Descriptions

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


## Features

- **Asset Management**: Add, view, edit, and remove assets.
- **User Authentication**: Sign up and log in with credential validation.
- **Financial Goals**: Track and manage financial goals.
- **Zakat Calculation**: Calculate Zakat based on asset types and durations.
- **Bank Account Validation**: Verify card details and OTP.

---
