import React, {useEffect, useState} from 'react';
import {FormControl, FormHelperText, InputLabel, Select, SelectChangeEvent, styled} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import {Box, Grid} from "@mui/material/";
import {StudentFieldSelect} from "../../interface/StudentFieldType";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;
const Course:React.FC<StudentFieldSelect> = ({onChangeSelect, fieldErrorType}) => {
    const [course, setCourse] = useState<string>('');
    const [ErrorText, setErrorText] = useState<string>('');
    useEffect(() => {
        if (fieldErrorType === ''){
            setErrorText('');
        }else {
            setErrorText(fieldErrorType);
        }
    },[fieldErrorType])
    useEffect(() => {
        if (course != '') {
            if (ErrorText != ''){
                setErrorText('');
            }
        }
    },[course])
    const onChange = (e: SelectChangeEvent) => {
        let name = e.target.name;
        let value = e.target.value;
        onChangeSelect({name, value});
        setCourse(value);
    }
    return(
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                marginTop:1
            }}
        >
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <FormControl fullWidth>
                        <InputLabel id="studyType">수업코스</InputLabel>
                        <Select
                            labelId="studyType"
                            name='studyType'
                            id="studyType"
                            value={course}
                            autoWidth={false}
                            label="studyType"
                            onChange={onChange}
                            sx={{minWidth: 120}}

                        >
                            <MenuItem value={'A_CLASS'}>A</MenuItem>
                            <MenuItem value={'B_CLASS'}>B</MenuItem>
                            <MenuItem value={"C_CLASS"}>C</MenuItem>
                            <MenuItem value={'D_CLASS'}>D</MenuItem>
                            <MenuItem value={'A_CLASS_H'}>A 수료</MenuItem>
                            <MenuItem value={"B_CLASS_H"}>B 수료</MenuItem>
                        </Select>

                    </FormControl>
                    <FormHelperTexts>{ErrorText}</FormHelperTexts>
                </Grid>
            </Grid>
        </Box>
    )
}

export default Course;