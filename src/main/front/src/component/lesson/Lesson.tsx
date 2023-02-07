import React, {useRef} from 'react';
import {
    DataGrid,
    GridColDef, GridFilterForm, GridFooter, GridFooterContainer,
    gridPageCountSelector,
    gridPageSelector, GridToolbar, GridToolbarContainer, GridToolbarExport,
    useGridApiContext,
    useGridSelector
} from '@mui/x-data-grid';
import {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import CustomNoRowsOverlay from "../CustomNoRowsOverlay";
import CustomPagination from "../CustomPagination";
import LocalizedTextsMap from "../../LocalizedTextsMap";
import {Button} from "@mui/material/";
import dayjs, {Dayjs} from "dayjs";
import {FormControl, Grid, InputLabel, Paper, Select, SelectChangeEvent} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import LessonRegister from "./LessonRegister";
import {StudentType} from "../../interface/StudentFieldType";


type StudentList = {
    id: number,
    time: string,
    studentName: string,
    parentPhone: string,
    stepName: string
}
const isWeekend = (date: Dayjs) => {

    const day = date.day();

    return day === 0 || day === 7;
};
type DayOfStudyType = {
    studentId: string,
    studyId: string,
    studyDetail: string,
    currentStudyCount: string,
    studyType: string,
    lastDate: string,
    studentInfo?: StudentType,
    studyTypeString: string,
}

const Lesson: React.FC = () => {
    dayjs.locale('ko');
    const [value, setValue] = React.useState<Dayjs | null>(dayjs());
    const [daySelect, setDaySelect] = useState(value?.day() === undefined ? 0 : value.day());
    const [dayOfStudy, setDayOfStudy] = useState<DayOfStudyType>({
        studentId: '',
        studyId: '',
        studyDetail: '',
        currentStudyCount: '',
        studyType: "",
        lastDate: "",
        studentInfo: undefined,
        studyTypeString: ""
    });
    const [studyList, setStudyList] = useState(['']);

    const navigate = useNavigate();
    const [rows, setRows] = useState<readonly StudentList[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const dataGridRef = useRef<HTMLDivElement | null>(null);
    const columns: GridColDef[] = [
        {
            field: 'grade', headerName: '구분', width: 80, renderCell: (params) => (
                `${params.row.grade} ${params.row.gradeLv}`
            )
        },
        {
            field: 'studentName', headerName: '학생이름', width: 85, align: "right", renderCell: (params) => (
                <Button fullWidth onClick={() => {
                    getStudentStudy(params.row.id)
                }}>
                    {params.row.studentName}</Button>
            )
        },
        {field: 'stepName', headerName: '수업단계', width: 110},

    ];
    const onChangeDay = (e: SelectChangeEvent) => {
        let day = parseInt(e.target.value);
        setDaySelect(day);
    }
    const getStudentStudy = (id: string) => {
        axios.get(`/api/lesson/${id}`, {params: {daySelect}})
            .then(res => {
                setDayOfStudy(res.data.dayOfStudy);
                setStudyList(res.data.studyDetailList);
            }).catch(error => {
            console.log(error)
            if (error.response && error.response.status === 401) {
                navigate('/login');
            }
        });
    }

    useEffect(() => {
        setIsLoading(true);
        axios.get(`/api/schedule/day/${daySelect}`)
            .then(res => {
                setRows(res.data);
                setIsLoading(false);
            }).catch(error => {
            console.log(error)
            if (error.response.status === 401) {
                navigate('/login');
            }
        });
    }, [daySelect])

    return (
        <Grid container spacing={2}>
            <Grid item xs={12} sm={3}>
                <Paper
                    sx={{
                        p: 2,
                        display: 'flex',
                        flexDirection: 'column'
                    }}
                >
                    <FormControl size="small">
                        <InputLabel id="day">요일</InputLabel>
                        <Select
                            id="day"
                            value={daySelect.toString()}
                            label="요일"
                            onChange={onChangeDay}
                            sx={{minWidth: 100, maxWidth: 120}}
                            style={{marginBottom: 2}}

                        > <MenuItem disabled={true} value='0'>시간선택</MenuItem>
                            <MenuItem value='1'>월요일</MenuItem>
                            <MenuItem value='2'>화요일</MenuItem>
                            <MenuItem value='3'>수요일</MenuItem>
                            <MenuItem value='4'>목요일</MenuItem>
                            <MenuItem value='5'>금요일</MenuItem>
                            <MenuItem value='6'>토요일</MenuItem>
                        </Select>
                    </FormControl>
                    <div style={{height: 830, width: '100%'}} ref={dataGridRef}>

                        <DataGrid
                            rows={rows}
                            columns={columns}
                            pageSize={10}
                            rowsPerPageOptions={[20]}
                            components={{
                                NoRowsOverlay: (() => <CustomNoRowsOverlay noList={'오늘 수업 예정 학생이 없습니다.'}/>),
                                Pagination: CustomPagination,
                                Toolbar: GridToolbar
                            }}
                            loading={isLoading}
                            hideFooterSelectedRowCount={true}
                            localeText={LocalizedTextsMap}
                        />
                    </div>
                </Paper>
            </Grid>
            <Grid item xs={12} sm={9}>
                <LessonRegister dayOfStudy={dayOfStudy} dayCount={dayOfStudy.currentStudyCount} study={studyList}/>
            </Grid>

        </Grid>
    )
}
export default Lesson;

