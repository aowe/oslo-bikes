import React, { useState, useEffect, useMemo } from 'react';
import './App.css';
import Table from './components/table/Table';
import columns from './components/table/columnConfig';
import Header from './components/header/Header';

function App() {
  const memoizedColumns = useMemo(() => columns, []);

  const [bikeData, setBikeData] = useState([]);
  const tableData = useMemo(() => bikeData, [bikeData]);

  const fetchBikes = () => {
    fetch('/api/bikes')
        .then(response => {
          if (response.ok)
            return response.json()
        })
        .then(message => {
          setBikeData(message);
        });
  };

  useEffect(() => {
    fetchBikes();
  }, []);
  
  return (
    <div >
      <Header />
      <div className="content">
        <div className="btnContent">
          <button className="updateBtn" onClick={() => fetchBikes()}>Hent oppdatert data</button>
        </div>
        <Table data={tableData} columns={memoizedColumns} />
      </div>
    </div>
  );
}

export default App;
