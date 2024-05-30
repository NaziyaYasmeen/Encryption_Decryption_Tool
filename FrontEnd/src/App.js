
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import EncryptDecryptPage from './components/EncryptDecryptPage';
import EncryptDecryptFilePage from './components/EncryptDecryptFilePage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/home" element={<HomePage />} />
        <Route path="/encrypt-decrypt-text" element={<EncryptDecryptPage />} />
        <Route path="/encrypt-decrypt-file" element={<EncryptDecryptFilePage />} />
      </Routes>
    </Router>
  );
};

export default App;


