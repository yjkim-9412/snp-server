import React from 'react';
import {FormControl, InputLabel, Select, SelectChangeEvent} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";

const TimeSelector = () => {
    const [age, setAge] = React.useState('');

    const handleChange = (event: SelectChangeEvent) => {
        setAge(event.target.value as string);
    };
    return(
        <FormControl fullWidth size="small">
            <Select
                id="demo-simple-select"
                value={age}
                onChange={handleChange}
                sx={{minWidth: 100, maxWidth:120}}
                style={{marginBottom:2}}

            >
                <MenuItem value='11:10'>11:10분</MenuItem>
                <MenuItem value='14:20'>14:20분</MenuItem>
                <MenuItem value='16:15'>16:15분</MenuItem>
                <MenuItem value='16:20'>16:20분</MenuItem>
                <MenuItem value='17:55'>17:55분</MenuItem>
                <MenuItem value='19:30'>19:30분</MenuItem>
                <MenuItem value='20:30'>20:30분</MenuItem>
                <MenuItem value='20:30'>20:30분</MenuItem>
                <MenuItem value='20:30'>20:30분</MenuItem>
                <MenuItem value='21:00'>21:00분</MenuItem>
                <MenuItem value='21:05'>21:05분</MenuItem>
                <MenuItem value='21:30'>21:30분</MenuItem>
            </Select>
        </FormControl>
    )
}

export default TimeSelector;