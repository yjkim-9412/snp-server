import { Grid, Paper } from '@mui/material';
import React from 'react'
import StudentList from './StudentList';

const Student = () => {
    return (
        <Grid container spacing={3}>
            <StudentList/>
        </Grid>
    )
}

export default Student;