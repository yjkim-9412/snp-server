import {Grid, Paper} from '@mui/material';
import React, {useEffect, useRef, useState} from 'react'
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {
    DataGrid, getGridStringOperators,
    GridColDef,
    GridFooter,
    GridFooterContainer, GridToolbar,
    GridToolbarContainer,
    GridToolbarExport
} from "@mui/x-data-grid";
import CustomNoRowsOverlay from "../CustomNoRowsOverlay";
import CustomPagination from "../CustomPagination";
import AppBarComp from "../AppBarComp";
import Box from "@mui/material/Box";
import LocalizedTextsMap from "../../LocalizedTextsMap";

type StudentList = {
    [student: string]: string;
}

const StudentList = () => {
    const navigate = useNavigate();
    const [rows, setRows] = useState<readonly StudentList[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const dataGridRef = useRef<HTMLDivElement | null>(null);
    const columns: GridColDef[] = [
        {
            field: 'name', headerName: '학생이름', width: 125,  headerAlign: 'center', align:'center', renderCell: (params) => (
                <Link to={`/students/info/${params.row.id}`} style={{marginLeft: 10}}>{params.row.name}</Link>
            )
        },
        {field: 'age', headerName: '나이', width: 70},
        {field: 'gender', headerName: '성별', width: 70},
        {field: 'phone', headerName: '연락처', width: 125},
        {field: 'gradeToString', headerName: '구분', width: 100},
        {field: 'gradeLv', headerName: '학년', width: 80,renderCell: (params) => (
                `${params.row.gradeLv} 학년`
            )},
        {field: 'studyTypeToString', headerName: '수업과정', width: 100, align:'center'},
        {field: 'stepName', headerName: '수업단계', width: 125},
        {field: 'studyCount', headerName: '단계일수', width: 80,align:'center'},
        {field: 'date', headerName: '등록일자', width: 125},
        {field: 'parentName', headerName: '학부모이름', width: 100},
        {field: 'parentPhone', headerName: '학부모연락처', width: 125},
        {field: 'speed', headerName: '습관검사', width: 80,align:'center'},
        {field: 'readLv', headerName: '독서검사', width: 80,align:'center'},
        {field: 'intLv', headerName: '지능검사', width: 80,align:'center'},
        {
            field: 'registration', headerName: '등록여부', width: 100, renderCell: (params) => (
                params.row.registration ? '등록생' : '예비생'
            )
        },


    ];

    function CustomToolbar() {
        return (
            <GridToolbarContainer>
                <GridToolbarExport csvOptions={{fileName: '학생목록'}}/>
            </GridToolbarContainer>
        );
    }

    function CustomFooter() {
        return (
            <GridFooterContainer>
                <GridFooter sx={{
                    border: 'none',
                }}/>
                <CustomToolbar/>
            </GridFooterContainer>
        );
    }

    useEffect(() => {
        setIsLoading(true);
        axios.get('/api/students/list')
            .then(res => {
                setRows(res.data)
                console.log('data = ' + res.data.registration)
            })
            .catch(error => {
                if (error.response.status === 401) {
                    navigate('/logout');
                } else {
                    console.log(error);
                }
            }).finally(() => setIsLoading(false));
    }, []);

    return (
        <Grid item xs={12}>
            <Paper
                sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column'
                }}
            >
                <AppBarComp typography={'학생 정보'}/>

                <Box display='flex'>
                    <div style={{minHeight: 740, width: '100%'}} ref={dataGridRef}>
                        <DataGrid
                            rows={rows}
                            columns={columns}
                            pageSize={10}
                            rowsPerPageOptions={[30]}
                            components={{
                                NoRowsOverlay: (() => <CustomNoRowsOverlay noList={'학생목록이 없습니다.'}/>),
                                Pagination: CustomPagination,
                                Toolbar: GridToolbar
                            }}

                            loading={isLoading}
                            hideFooterSelectedRowCount={true}
                            localeText={LocalizedTextsMap}
                        />
                    </div>
                </Box>
            </Paper>
        </Grid>)
}
export default StudentList;