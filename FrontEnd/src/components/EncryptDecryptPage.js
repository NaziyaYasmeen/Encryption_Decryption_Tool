// EncryptDecryptPage.js
import React, { useState } from 'react';
import EncryptionComponent from './EncryptionComponent';
import DecryptionComponent from './DecryptionComponent';
import './EncryptDecrypt.css';

const EncryptDecryptPage = () => {
  const [activeTab, setActiveTab] = useState('encrypt');

  return (
    <div className="encrypt-decrypt-page">
      <h1>CryptoShield to Encrypt and Decrypt</h1>
      <div className="tabs">
        <button 
          className={`tab ${activeTab === 'encrypt' ? 'active' : ''}`} 
          onClick={() => setActiveTab('encrypt')}
        >
          Encrypt
        </button>
        <button 
          className={`tab ${activeTab === 'decrypt' ? 'active' : ''}`} 
          onClick={() => setActiveTab('decrypt')}
        >
          Decrypt
        </button>
      </div>
      <div className="tab-content">
        {activeTab === 'encrypt' ? <EncryptionComponent /> : <DecryptionComponent />}
      </div>
    </div>
  );
};

export default EncryptDecryptPage;
