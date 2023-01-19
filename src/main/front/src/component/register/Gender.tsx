import React, {useState} from 'react';
import {
    FormControlLabel,
    FormLabel,
    Radio,
    RadioGroup,

} from "@mui/material";
import {StudentFieldRadio, StudentFieldType} from "../../interface/StudentFieldType";
import {FormControl} from "@mui/material/";

const Gender:React.FC<StudentFieldRadio> = ({onChangeRadio, studentValue, textType}) => {

    return (<FormControl>
            <FormLabel id="gender-label" style={{marginLeft:10}}>성별</FormLabel>
            <RadioGroup style={{marginLeft:18, marginRight:32}}
                row
                aria-labelledby="gender-label"
                name="gender"
            >
                <FormControlLabel value="여자" control={<Radio/>} label="여자" name={textType} onClick={(e) => onChangeRadio(e,"여자")}/>
                <FormControlLabel value="남자" control={<Radio/>} label="남자" name={textType} onClick={(e) => onChangeRadio(e,"남자")}/>
            </RadioGroup>
        </FormControl>
    );
}
export default Gender;
