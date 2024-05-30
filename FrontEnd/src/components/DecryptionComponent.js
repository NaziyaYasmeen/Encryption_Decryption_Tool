
import React, { useState } from 'react';
import axios from 'axios';
import './EncryptDecrypt.css';

const DecryptionComponent = () => {
  const [encryptedText, setEncryptedText] = useState('');
  const [decryptedText, setDecryptedText] = useState('');
  const [error, setError] = useState('');

  const handleDecrypt = async () => {
    if (encryptedText.trim() === '') {
      setError('Please enter some text.');
      return;
    }
    try {
      const response = await axios.post('http://localhost:8082/decrypt', encryptedText, {
        headers: { 'Content-Type': 'text/plain' },
      });
      setDecryptedText(response.data);
      setError('');
    } catch (error) {
      setError('Decryption failed.');
    }
  };

  return (
    <div className="content">
      <h2>Decrypt</h2>
      <div className="input-section">
        <textarea
          rows="4"
          placeholder="Enter text to decrypt"
          value={encryptedText}
          onChange={(e) => setEncryptedText(e.target.value)}
        ></textarea>
        {error && <p className="error">{error}</p>}
      </div>
      <button className="decrypt-button" onClick={handleDecrypt}>Decrypt</button>
      {decryptedText && (
        <div className="output-section">
          <h3>Decrypted Text</h3>
          <textarea rows="4" readOnly value={decryptedText}></textarea>
        </div>
      )}
    </div>
  );
};

export default DecryptionComponent;
