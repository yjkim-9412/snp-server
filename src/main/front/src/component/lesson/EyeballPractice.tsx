import React, {useEffect, useState} from 'react'
import {Paper, styled} from "@mui/material";
import TextField from "@mui/material/TextField";
import PropsAction from "../../interface/PropsAction";
import Typography from "@mui/material/Typography";
import {Grid} from "@mui/material/";

const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    .MuiFormHelperText-root {
  color: #FF0000 !important;
}
`;
type ConcentrationFieldType = {

    getRapidEyeball?: string,
    getEyeBallCount?: string,

    onChange: (name:string, value:string) => void
}
const EyeballPractice:React.FC<ConcentrationFieldType> = ({getRapidEyeball,
                                                              getEyeBallCount,onChange}) => {
    const [eyeball, setEyeball] = useState({rapidEyeball:'',eyeBallCount:'' });

    const [errorField, setErrorField] = useState({rapidEyeball:'',eyeBallCount:'' });

    const onChangeField = (e: PropsAction) => {
        let value = e.target.value
        let name = e.target.name
        if (value === ''){
            setErrorField({...errorField,[name]: '필수 값'});
        }else {
            setErrorField({...errorField,[name]: ''});
        }
        setEyeball({...eyeball,[name]:value})
        onChange(name, value);
    }
    useEffect(()=>{
        if (typeof getRapidEyeball === "string") {
            setEyeball({...eyeball,rapidEyeball: getRapidEyeball});
        }
    },[getRapidEyeball]);
    useEffect(()=>{
        if (typeof getEyeBallCount === "string") {
            setEyeball({...eyeball,eyeBallCount: getEyeBallCount});
        }
    },[getEyeBallCount]);
    return(
        <Paper
            sx={{
            display: 'flex',
        }}>
            <Grid item xs={12} >
            <Typography sx={{p:1, fontSize:12}}>안구 훈련</Typography>
            <TextFields size='small' type='number' name='rapidEyeball' label='전체완료 수' variant="outlined"
                        sx={{width:100,marginLeft:1,marginRight:1}} helperText={errorField.rapidEyeball} error={errorField.rapidEyeball !== ''}
                        value={eyeball.rapidEyeball} focused onChange={onChangeField}
            />
            <TextFields size='small' name='eyeBallCount' label='중간완료' variant="outlined"
                        sx={{width:100,marginLeft:1,marginRight:1}} helperText={errorField.eyeBallCount} error={errorField.eyeBallCount !== ''}
                        value={eyeball.eyeBallCount} focused onChange={onChangeField}
            />
            </Grid>

        </Paper>
    )
}
export default EyeballPractice;