import type { NextPage } from 'next'
import Head from 'next/head'
import Widgets from '../common/components/elements/widgets'
import Tasks from "../common/components/elements/tasks";

const Home: NextPage = () => {
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
          <br />
          <Tasks />
        </div>
      </main>
    </div>
  )
}

export default Home
