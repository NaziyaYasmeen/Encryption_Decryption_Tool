import React, { useState } from 'react';
import axios from 'axios';
import './EncryptDecrypt.css';

const FileEncryptionComponent = () => {
  const [file, setFile] = useState(null);
  const [encryptedText, setEncryptedText] = useState('');
  const [error, setError] = useState('');

  const handleFileSelect = (event) => {
    setFile(event.target.files[0]);
  };

  const handleEncrypt = async () => {
    if (!file) {
      setError('Please select a file to encrypt.');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8083/encryptFile', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
      setEncryptedText(response.data);
      setError('');
    } catch (error) {
      setError('File encryption failed.');
    }
  };

  return (
    <div className="content">
      <h2>File Encryption</h2>
      <div className="input-section">
        <input type="file" onChange={handleFileSelect} />
        <button className='file-encrypt-button' onClick={handleEncrypt}>Encrypt</button>
        {error && <p className="error">{error}</p>}
      </div>
      {encryptedText && (
        <div className="output-section">
          <h3>Encrypted Text</h3>
          <textarea rows="4" readOnly value={encryptedText}></textarea>
        </div>
      )}
    </div>
  );
};

export default FileEncryptionComponent;
