import {Grid, Paper, Table, TableBody, TableCell, TableHead, TableRow} from '@mui/material';
import React, {useEffect, useState} from 'react'
import {Title} from "@mui/icons-material";
import Link from "@mui/material/Link";
import axios from "axios";


const StudentList = () => {
    const [students, setStudents] = useState();
    useEffect(() => {
        axios.get('')
        }
    )

    return (
        <Grid item xs={12}>
            <Paper
                sx={{
                    p: 2,
                    display: 'flex',
                }}
            >
                <Title>학생리스트</Title>
                <Table size="small">
                    <TableHead>
                        <TableRow>
                            <TableCell>Date</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Ship To</TableCell>
                            <TableCell>Payment Method</TableCell>
                            <TableCell align="right">Sale Amount</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {/*{rows.map((row) => (*/}
                        {/*    <TableRow key={row.id}>*/}
                        {/*        <TableCell>{row.date}</TableCell>*/}
                        {/*        <TableCell>{row.name}</TableCell>*/}
                        {/*        <TableCell>{row.shipTo}</TableCell>*/}
                        {/*        <TableCell>{row.paymentMethod}</TableCell>*/}
                        {/*        <TableCell align="right">{`$${row.amount}`}</TableCell>*/}
                        {/*    </TableRow>*/}
                        {/*))}*/}
                    </TableBody>
                </Table>
            </Paper>
        </Grid>)
}
export default StudentList;