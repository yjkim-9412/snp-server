import {ChangeEvent} from "react";

interface ChangeProps extends ChangeEvent<HTMLInputElement>{
    target: HTMLInputElement & EventTarget
}

export default ChangeProps;