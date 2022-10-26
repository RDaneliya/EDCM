import React from 'react';
import './App.css';
import Commodities from './components/Commodities';
import { BrowserRouter, Route, Routes, } from 'react-router-dom';
import Header from './components/Header';
import Home from './components/Home';

export const App = () => {
    return (
        <>
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/Commodities' element={<Commodities />} />
                </Routes>
            </BrowserRouter>
        </>)
};