import logo from './logo.svg';
import './App.css';
import Login from './Login';
import Profile from './Profile';
import Manager from './Manager';
import { Route, Routes } from 'react-router-dom';
import AuthenticatedRoute from './AuthenticatedRoute';
function App() {
  return (
    <Routes>
        <Route exact path="/" element={<Login />}/>
        <Route path="/login" element={<Login />}/>
        <Route path='/manager' element={<AuthenticatedRoute Component={Manager} />}/>
        <Route path='/profile' element={<AuthenticatedRoute Component={Profile} />}/>
        

      </Routes>
  );
}

export default App;
