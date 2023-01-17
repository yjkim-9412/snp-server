import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';
import 'dayjs/locale/ko';
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { StaticDatePicker } from '@mui/x-date-pickers/StaticDatePicker';
import TodayStudent from "./TodayStudent";
import {Container} from "@mui/material/";
import Grid2 from "@mui/material/Unstable_Grid2";
import {Box} from "@mui/material";


const isWeekend = (date: Dayjs) => {

    const day = date.day();

    return day === 0 || day === 7;
};

const CalendarMain = () => {
    dayjs.locale('ko');
    const [value, setValue] = React.useState<Dayjs | null>(dayjs());
    const dayOfWeek = value?.day();
    return (
        <>
        <Box display="flex" >
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <StaticDatePicker
                orientation="landscape"
                openTo="day"
                value={value}
                shouldDisableDate={isWeekend}
                onChange={(newValue) => {
                    setValue(newValue);
                }}
                renderInput={(params) => <TextField {...params} />}
                componentsProps={{
                    actionBar: {
                        actions: ['today'],
                    },
                }}
            />
        </LocalizationProvider>
        </Box>
        <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {
                dayOfWeek === undefined ?
                    <TodayStudent dayOfWeek={0}/>
                    : <TodayStudent dayOfWeek={dayOfWeek}/>

            }
        </Box>
        </>


);
}
export default CalendarMain;
