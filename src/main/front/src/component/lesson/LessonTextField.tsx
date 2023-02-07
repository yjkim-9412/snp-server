import React from 'react';
import TextField from "@mui/material/TextField";
import {FormControl, InputLabel} from "@mui/material";

type TextFieldType = {
    name?: string,
    value?: string,
    label?: string,
    widthValue: number
}
const LessonTextField: React.FC<TextFieldType> = ({widthValue, name, value, label}) => {
    return (
            <TextField size='small' id={name} label={label} name={name} variant="standard"
                       sx={{width: widthValue, marginLeft: 1, marginRight: 1, display:'flex'}}
                       inputProps={{readOnly: true,}} value={value} focused/>
    )
}

export default LessonTextField;