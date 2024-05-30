import React from 'react';
import { Link } from 'react-router-dom';
import './HomePage.css';

const HomePage = () => {
  return (
    <div className="home" style={{ backgroundImage: `url(${process.env.PUBLIC_URL}/background.jpg)` }}>
      <div className="overlay d-flex flex-column justify-content-center align-items-center">
        <h1 className="text-center mb-4">Welcome to CryptoShield</h1>
        <p className="text-center text-blue italic mb-4">Unlock the secrets with our cutting-edge encryption and decryption services.</p>
        <p className="text-center text-blue italic mb-4"> Explore our solutions for safeguarding your information and ensuring confidentiality at every step.</p>
        <div className="d-flex justify-content-center">
          <br></br>
          <Link to="/encrypt-decrypt-text" className="btn btn-primary mr-3">Encrypt/Decrypt Text</Link>
          <Link to="/encrypt-decrypt-file" className="btn btn-primary">Encrypt/Decrypt File</Link>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
