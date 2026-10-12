import { useState } from 'react';
import './App.css';

const features = [
  {
    icon: '💊',
    title: 'Medicine Management',
    description: 'Track stock, expiry and batches',
  },
  {
    icon: '👥',
    title: 'Customer Management',
    description: 'Serve your customers better',
  },
  {
    icon: '🚚',
    title: 'Supplier Management',
    description: 'Build stronger partnerships',
  },
  {
    icon: '📊',
    title: 'Sales & Reports',
    description: 'Make smarter business decisions',
  },
];

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);
  const [message, setMessage] = useState('');

  function handleLogin(event) {
    event.preventDefault();

    if (!username.trim() || !password) {
      setMessage('Please enter your username and password.');
      return;
    }

    setMessage(
        'Your form is ready! Backend authentication will be connected next.'
    );
  }

  return (
      <main className="login-page">
        <section className="login-layout">

          {/* Left branding panel */}
          <div className="brand-panel">
            <header className="brand-header">
              <div className="brand-logo">✚</div>

              <div className="brand-name">
                <h2>Medical Store</h2>
                <p>Management System</p>
              </div>

              <span className="brand-tagline">
              Better Health.
              <br />
              Brighter Tomorrow.
            </span>
            </header>

            <div className="brand-content">
              <span className="eyebrow">YOUR HEALTHCARE PARTNER</span>

              <h1>
                Smart Management
                <br />
                for a <span>Healthier Tomorrow.</span>
              </h1>

              <p className="brand-description">
                Manage your medicines, customers, suppliers and sales
                through one simple, intelligent platform.
              </p>

              <div className="feature-list">
                {features.map((feature) => (
                    <div className="feature-item" key={feature.title}>
                      <div className="feature-icon">{feature.icon}</div>

                      <div>
                        <h3>{feature.title}</h3>
                        <p>{feature.description}</p>
                      </div>
                    </div>
                ))}
              </div>
            </div>

            <footer className="brand-footer">
              <span>✦</span>
              Smarter operations. Better healthcare.
            </footer>
          </div>

          {/* Right login panel */}
          <div className="login-panel">
            <div className="login-card">

              <div className="login-logo">
                <span>✚</span>
              </div>

              <h2 className="login-heading">Medical Store</h2>

              <p className="login-subtitle">
                Management System
              </p>

              <div className="heading-divider">
                <span />
              </div>

              <div className="welcome-section">
                <h1>Welcome Back!</h1>
                <p>Sign in to your account to continue</p>
              </div>

              <form onSubmit={handleLogin} className="login-form">
                <div className="input-wrapper">
                <span className="input-icon" aria-hidden="true">
                  ♙
                </span>

                  <input
                      type="text"
                      name="username"
                      placeholder="Username"
                      aria-label="Username"
                      autoComplete="username"
                      value={username}
                      onChange={(event) => {
                        setUsername(event.target.value);
                        setMessage('');
                      }}
                      required
                  />
                </div>

                <div className="input-wrapper">
                <span className="input-icon" aria-hidden="true">
                  ♙
                </span>

                  <input
                      type={showPassword ? 'text' : 'password'}
                      name="password"
                      placeholder="Password"
                      aria-label="Password"
                      autoComplete="current-password"
                      value={password}
                      onChange={(event) => {
                        setPassword(event.target.value);
                        setMessage('');
                      }}
                      required
                  />

                  <button
                      type="button"
                      className="password-toggle"
                      onClick={() => setShowPassword(!showPassword)}
                      aria-label={showPassword ? 'Hide password' : 'Show password'}
                  >
                    {showPassword ? 'Hide' : 'Show'}
                  </button>
                </div>

                <div className="login-options">
                  <label className="remember-option">
                    <input
                        type="checkbox"
                        checked={rememberMe}
                        onChange={(event) => setRememberMe(event.target.checked)}
                    />

                    <span>Remember me</span>
                  </label>

                  <button
                      type="button"
                      className="text-button"
                      onClick={() =>
                          setMessage(
                              'Please contact your administrator to reset your password.'
                          )
                      }
                  >
                    Forgot Password?
                  </button>
                </div>

                <button type="submit" className="login-button">
                  <span>↪</span>
                  Sign In to Your Account
                </button>

                {message && (
                    <p className="form-message" role="status">
                      {message}
                    </p>
                )}
              </form>

              <div className="login-separator">
                <span />
                <p>SECURE ACCESS</p>
                <span />
              </div>

              <div className="security-note">
                <span>🔒</span>
                <p>Secure <b>·</b> Reliable <b>·</b> Trusted</p>
              </div>

              <p className="copyright">
                © {new Date().getFullYear()} Medical Store Management System
              </p>
            </div>
          </div>
        </section>
      </main>
  );
}

export default App;