import type { NextPage } from 'next'
import Head from 'next/head'
import Widgets from '../common/components/elements/widgets'
import Tasks from "../common/components/elements/tasks";
import { useState } from "react"

const Home: NextPage = () => {

    const [ gridData, setGridData ] = useState<[
        {
            taskNo : number,
            taskNo : number,
            custmName : string,
            title : string,
            priorityType : string,
            requireType : string,
            errorType : string,
            receiptDate : string,
            finishedDate : string,
            stepType : string
        }
    ]|null>();

    const applyGridData = ( gridData : [number]|null) : void => {
        setGridData(gridData)
    }

    return (
        <>
            <Head>
                <title>Working Progressive</title>
                <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="card">
                <div className="card-body">
                  <Widgets applyFunc={applyGridData} />
                  <Tasks gridData={gridData} />
                </div>
            </div>
        </>
    )
}

export default Home
