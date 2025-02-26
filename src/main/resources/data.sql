
-- Insert sample users with initial balances
INSERT INTO user_balances (user_id, amount) VALUES
('user123', 1000.00),
('user456', 2500.00),
('user789', 500.00);

-- Insert sample merchants with initial balances
INSERT INTO merchant_balances (merchant_id, amount) VALUES
('merchant001', 5000.00),
('merchant002', 7500.00),
('merchant003', 3000.00);

-- Insert sample completed transactions
INSERT INTO transactions (id, amount, currency, merchant_id, user_id, description, status, created_at, updated_at) VALUES
('trans-001', 150.00, 'NGN', 'merchant001', 'user123', 'Purchase of electronics', 'COMPLETED', '2025-02-24 10:30:00', '2025-02-24 10:35:00'),
('trans-002', 75.50, 'USD', 'merchant002', 'user456', 'Software subscription', 'COMPLETED', '2025-02-24 13:15:00', '2025-02-24 13:20:00'),
('trans-003', 200.00, 'EUR', 'merchant003', 'user789', 'Consulting services', 'COMPLETED', '2025-02-24 15:45:00', '2025-02-24 15:50:00');

-- Insert sample pending transactions
INSERT INTO transactions (id, amount, currency, merchant_id, user_id, description, status, created_at, updated_at) VALUES
('trans-004', 300.00, 'NGN', 'merchant001', 'user456', 'Home appliance purchase', 'PENDING', '2025-02-25 09:00:00', '2025-02-25 09:00:00'),
('trans-005', 125.75, 'GBP', 'merchant002', 'user123', 'Online course payment', 'PENDING', '2025-02-25 10:30:00', '2025-02-25 10:30:00');

-- Insert sample failed transactions
INSERT INTO transactions (id, amount, currency, merchant_id, user_id, description, status, created_at, updated_at) VALUES
('trans-006', 1500.00, 'USD', 'merchant003', 'user789', 'Premium membership', 'FAILED', '2025-02-24 16:00:00', '2025-02-24 16:05:00');
