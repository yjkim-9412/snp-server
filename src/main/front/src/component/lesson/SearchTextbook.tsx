import React, {useEffect, useState} from 'react';
import {FormHelperText, Grid, Paper} from "@mui/material";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material/";
import TextBookSearchModal from "../textbook/TextBookSearchModal";
import PropsAction from "../../interface/PropsAction";
import QuestionScore from "./QuestionScore";


type  SearchTextbookType = {
    onChange: (name: string, value: string) => void
    onChangeNumber: (name: string, value: number) => void
    getScore?: answerType,
    onChangeAnswer: (name: string, value: string) => void
    getMin:string,
    getSec:string
}
type answerType = {
    [answer: string]: string;
}

const SearchTextbook: React.FC<SearchTextbookType> = ({onChange, onChangeNumber, getScore, onChangeAnswer,getSec,getMin}) => {
    const [code, setCode] = useState('');
    const [textBook, setTextBook] = useState({code: '', detail: '', name: ''});
    const [answer, setAnswer] = useState<answerType>({
        1: '', 2: '', 3: '', 4: '', 5: '', 6: '', 7: '', 8: '', 9: '', 10: ''
    });
    const [questionCount, setQuestionCount] = useState(0);
    const [readCount, setReadCount] = useState('');
    const [processingTime, setProcessingTime] = useState({processingMin: '', processingSec: ''});
    const [processTimeError, setProcessTimeError] = useState('');
    const [readCountError, setReadCountError] = useState('');


    const onchangeTextBook = (name: string, value: string) => {
        if (name === 'code') {
            onChange('textBookCode', value);
        }
        setTextBook({...textBook, [name]: value})
    }
    const onChangeCount = (name: string, value: string) => {
        let valueInt = parseInt(value);
        setQuestionCount(valueInt)
        onChangeNumber(name,valueInt)
    }
    const onChangeReadCount = (e: PropsAction) => {
        let value = e.target.value;
        if (value.length > 4) {
            setReadCountError('최대 9999');
            return
        }
        setReadCountError('')
        setReadCount(value);
        onChange('readCount', value);
    }
    const onChangeTime = (e: PropsAction) => {
        let name = e.target.name;
        let value = e.target.value;
        let valueInt = parseInt(e.target.value);
        if (valueInt > 60) {
            setProcessTimeError('최대 60');
            return
        }
        setProcessTimeError('')
        setProcessingTime({...processingTime, [name]: value});
        onChange(name, value);
    }
    useEffect(() => {
        if (getScore !== undefined) {
            setAnswer(getScore);
        }
    }, [getScore])
    useEffect(() => {
        if (getMin !== '') {
            setProcessingTime({...processingTime,processingMin: getMin})
        }
    },[getMin])
    useEffect(() => {
        if (getSec !== '') {
            setProcessingTime({...processingTime,processingSec: getSec})
        }
    },[getSec])

    return (
        <>
            <Paper
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    p: 1
                }}>
                <Grid item xs={12}>
                    <Typography sx={{p: 1, fontSize: 12, float: 'left'}}>수업자료</Typography>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        size='small' name='code' label='교재코드' variant="outlined"
                        sx={{width: 100, marginLeft: 1, marginRight: 1, marginBottom: 1, float: 'left'}}
                        value={textBook.code} inputProps={{readOnly: true}}
                    />
                    <TextBookSearchModal onChange={onchangeTextBook} onChangeCount={onChangeCount}/>

                </Grid>
                <Grid item xl={12} sx={{float: 'left'}}>
                    <TextField
                        size='small' type='number' name='readCount' label='읽은 글자수' variant="standard"
                        sx={{width: 110, marginLeft: 1, marginRight: 1, marginBottom: 1}}
                        value={readCount} onChange={onChangeReadCount} helperText={readCountError} error={readCountError !== ''}
                    />
                    <TextField
                        size='small' type='number'  name='processingMin' label='소요시간 분' variant="standard"
                        sx={{width: 100, marginLeft: 1, marginRight: 1, marginBottom: 1}}
                        value={processingTime.processingMin} onChange={onChangeTime} helperText={processTimeError} error={processTimeError !== ''}
                    />
                    <TextField
                        size='small' type='number' name='processingSec' label='소요시간 초' variant="standard"
                        sx={{width: 100, marginLeft: 1, marginRight: 1, marginBottom: 1}}
                        value={processingTime.processingSec} onChange={onChangeTime} helperText={processTimeError} error={processTimeError !== ''}
                    />
                </Grid>

            </Paper>
            <Paper
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    p: 1
                }}>
                <Typography sx={{p: 1, fontSize: 12, float: 'left', marginBottom:1}}>문제 점수</Typography>
                <Grid item xs={12}>
                    <Grid>
                        <QuestionScore num={'1'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["1"]}/>
                        <QuestionScore num={'2'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["2"]}/>
                        <QuestionScore num={'3'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["3"]}/>
                        <QuestionScore num={'4'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["4"]}/>
                        <QuestionScore num={'5'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["5"]}/>
                    </Grid>
                    <Grid>
                        <QuestionScore num={'6'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["6"]}/>
                        <QuestionScore num={'7'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["7"]}/>
                        <QuestionScore num={'8'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["8"]}/>
                        <QuestionScore num={'9'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["9"]}/>
                        <QuestionScore num={'10'} onChange={onChangeAnswer} questionCount={questionCount}
                                       getScore={answer["10"]}/>
                    </Grid>
                </Grid>
            </Paper>
        </>
    )
}

export default SearchTextbook;