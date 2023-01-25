import React, {useEffect, useState} from 'react';
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import {Checkbox, FormControlLabel, FormGroup, InputLabel, Paper, Select, SelectChangeEvent} from "@mui/material";
import Box from "@mui/material/Box";
import {Button, FormControl, Grid} from "@mui/material/";
import MenuItem from "@mui/material/MenuItem";
import TimeSelector from "./TimeSelector";
import axios from "axios";

type StudentId = {
    studentId?:string
}
const Schedule: React.FC<StudentId> = ({studentId}) => {
    const [schedule, setSchedule] = useState();
    useEffect(()=>{
        axios.get('/api/students/info/'+studentId)
            .then(res => {
                console.log(res.data);
            })
    })
    return (
        <>
            <AppBar
                color="default"
                elevation={0}
                sx={{
                    position: 'relative',
                }}
            >
                <Toolbar>
                    <Typography component="h1" variant="h5">
                        시간표
                    </Typography>
                </Toolbar>
            </AppBar>
            <Paper
                sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column'
                }}
            >
                <Box display="flex">
                    <Box component="form" noValidate sx={{mt: 3}}>
                        <Grid  container spacing={6} >
                            <Grid item xs={4}>
                                <FormGroup>
                                    <FormControlLabel control={<Checkbox/>} label="월요일"/>
                                    <FormControlLabel control={<Checkbox/>} label="화요일"/>
                                    <FormControlLabel control={<Checkbox/>} label="수요일"/>
                                    <FormControlLabel control={<Checkbox/>} label="목요일"/>
                                    <FormControlLabel control={<Checkbox/>} label="금요일"/>
                                    <FormControlLabel control={<Checkbox/>} label="토요일"/>
                                </FormGroup>
                            </Grid>
                            <Grid item xs={6}>
                                <TimeSelector/>
                                <TimeSelector/>
                                <TimeSelector/>
                                <TimeSelector/>
                                <TimeSelector/>
                                <TimeSelector/>

                            </Grid>
                        </Grid>
                        <Grid container spacing={1}>
                            <Grid item xs={1} sm={1}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{mt: 3, mb: 2}}
                                >
                                    저장
                                </Button>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Paper>
        </>
    )
}
export default Schedule;