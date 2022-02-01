// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next'

type Data = {
    projectNo: number,
    taskTitle: string,
    startDate: string,
    status: string,
    team: [{name : string}],
    progress: number
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<Data[]>
) {
    res.status(200).json(
        //{ projectNo : 1, taskTitle: "New admin Design", startDate : "02/5/2019", status : "Completed", team : [{name : "Neil Wing"}, progress : 78} ]

    )
}
