
import React, { useState } from 'react';
import axios from 'axios';
import './EncryptDecrypt.css';

const EncryptionComponent = () => {
  const [inputText, setInputText] = useState('');
  const [encryptedText, setEncryptedText] = useState('');
  const [error, setError] = useState('');
  const [copySuccess, setCopySuccess] = useState('');

  const handleEncrypt = async () => {
    if (inputText.trim() === '') {
      setError('Please enter some text.');
      return;
    }
    try {
      const response = await axios.post('http://localhost:8081/encrypt', inputText, {
        headers: { 'Content-Type': 'text/plain' },
      });
      setEncryptedText(response.data);
      setError('');
      setCopySuccess(''); // Clear previous copy success message
    } catch (error) {
      setError('Encryption failed.');
    }
  };

  const handleCopy = () => {
    navigator.clipboard.writeText(encryptedText).then(() => {
      setCopySuccess('Copied to clipboard!');
    }, (err) => {
      setCopySuccess('Failed to copy.');
    });
  };

  return (
    <div className="content">
      <h2>Encrypt</h2>
      <div className="input-section">
        <textarea
          rows="4"
          placeholder="Enter text to encrypt"
          value={inputText}
          onChange={(e) => setInputText(e.target.value)}
        ></textarea>
        {error && <p className="error">{error}</p>}
      </div>
      <button className="encrypt-button" onClick={handleEncrypt}>Encrypt</button>
      {encryptedText && (
        <div className="output-section">
          <h3>Encrypted Text</h3>
          <textarea rows="4" readOnly value={encryptedText}></textarea>
          <button className="copy-button" onClick={handleCopy}>Copy</button>
          {copySuccess && <p className="copy-success">{copySuccess}</p>}
        </div>
      )}
    </div>
  );
};

export default EncryptionComponent;
