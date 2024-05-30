import React, { useState } from 'react';
import axios from 'axios';
import './EncryptDecrypt.css';

const FileDecryptionComponent = () => {
  const [file, setFile] = useState(null);
  const [decryptedText, setDecryptedText] = useState('');
  const [error, setError] = useState('');

  const handleFileSelect = (event) => {
    setFile(event.target.files[0]);
  };

  const handleDecrypt = async () => {
    if (!file) {
      setError('Please select a file to decrypt.');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8084/decryptFile', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
      setDecryptedText(response.data);
      setError('');
    } catch (error) {
      setError('File decryption failed.');
    }
  };

  return (
    <div className="content">
      <h2>File Decryption</h2>
      <div className="input-section">
        <input type="file" onChange={handleFileSelect} />
        <button className="file-decrypt-button"onClick={handleDecrypt}>Decrypt</button>
        {error && <p className="error">{error}</p>}
      </div>
      {decryptedText && (
        <div className="output-section">
          <h3>Decrypted Text</h3>
          <textarea rows="4" readOnly value={decryptedText}></textarea>
        </div>
      )}
    </div>
  );
};

export default FileDecryptionComponent;
