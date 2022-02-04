import type { NextPage } from 'next'
import Head from 'next/head'
import useSWR from 'swr'
import Widgets from '../common/components/elements/widgets'
import Tasks from "../common/components/elements/tasks";

const fetcher = (url: String) => {
    return fetch(url).then((res) => res.text());
}

const Home: NextPage = () => {
    const { data, error } = useSWR('/api/hello', fetcher)

    console.log(data)

    return (
        <div>
            <Head>
                <title>Working Progressive</title>
                <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <main>
                <div className="container">
                  <Widgets />
                  <Tasks />
                </div>
            </main>
        </div>
    )
}

export default Home
