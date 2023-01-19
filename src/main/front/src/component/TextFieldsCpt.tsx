import React, {useState} from 'react';
import {CssBaseline, FormHelperText, Grid, styled, TextField, ThemeProvider, Typography} from "@mui/material";
import PropsAction from "../interface/PropsAction";
import {StudentFieldType} from "../interface/StudentFieldType";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;
const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
`;


const TextFieldsCpt:React.FC<StudentFieldType> = ({onChangeType, studentValue,textType,labelType}) => {
    return(
        <>
            <TextFields
                required
                fullWidth
                type="text"
                id={textType}
                name={textType}
                label={labelType}
                value={studentValue}
                onChange={onChangeType}
                // error={pwError!== ''}
            />
            <FormHelperTexts></FormHelperTexts>
        </>
    )
}
export default TextFieldsCpt;