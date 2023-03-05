import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from './Login';
import Profile from './Profile';
import Manager from './Manager';
import { BrowserRouter, Routes, Route } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />}>
          <Route path="profile" element={<Profile />} />
          <Route path="manager" element={<Manager />} />

        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

