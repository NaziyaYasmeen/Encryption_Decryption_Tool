import React, { useState } from 'react';
import FileEncryptionComponent from './FileEncryptionComponent';
import FileDecryptionComponent from './FileDecryptionComponent';
import './EncryptDecrypt.css';

const EncryptDecryptFilePage = () => {
  const [activeTab, setActiveTab] = useState('encrypt');

  return (
    <div className="encrypt-decrypt-page">
      <h1>CryptoShield to Encrypt and Decrypt Files</h1>
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
        {activeTab === 'encrypt' ? <FileEncryptionComponent /> : <FileDecryptionComponent />}
      </div>
    </div>
  );
};

export default EncryptDecryptFilePage;
