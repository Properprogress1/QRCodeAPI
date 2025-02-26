-- Drop tables if they exist (to ensure a clean setup)
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS user_balances;
DROP TABLE IF EXISTS merchant_balances;

-- Create user_balances table
CREATE TABLE user_balances (
    user_id VARCHAR(255) PRIMARY KEY,
    amount DECIMAL(15,2) NOT NULL DEFAULT 0
);

-- Create merchant_balances table
CREATE TABLE merchant_balances (
    merchant_id VARCHAR(255) PRIMARY KEY,
    amount DECIMAL(15,2) NOT NULL DEFAULT 0
);

-- Create transactions table
CREATE TABLE transactions (
    id VARCHAR(255) PRIMARY KEY,
    amount DECIMAL(15,2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    merchant_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_balances(user_id) ON DELETE CASCADE,
    FOREIGN KEY (merchant_id) REFERENCES merchant_balances(merchant_id) ON DELETE CASCADE
);

-- Indexes for optimization
CREATE INDEX idx_status ON transactions (status);
CREATE INDEX idx_created_at ON transactions (created_at);
CREATE INDEX idx_user_transactions ON transactions (user_id);
CREATE INDEX idx_merchant_transactions ON transactions (merchant_id);
