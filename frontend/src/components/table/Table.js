import React from 'react';
import ReactTable from 'react-table-6';
import "react-table-6/react-table.css"

const Table = ({ data, columns }) => (
    <ReactTable
        nextText="Neste"
        previousText="Forrige"
        pageText="Side"
        ofText="av"
        rowsText="rader per side"
        noDataText="Ingen data funnet"
        showPagination
        defaultPageSize={10}
        pageSizeOptions={[5, 10, 25, 50, 100]}
        data={data}
        columns={columns}
    />
);

export default Table;
