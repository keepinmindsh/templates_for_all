This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started

First, run the development server:

```bash
npm run dev
# or
yarn dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `pages/register.tsx`. The page auto-updates as you edit the file.

[API routes](https://nextjs.org/docs/api-routes/introduction) can be accessed on [http://localhost:3000/api/hello](http://localhost:3000/api/hello). This endpoint can be edited in `pages/api/hello.ts`.

The `pages/api` directory is mapped to `/api/*`. Files in this directory are treated as [API routes](https://nextjs.org/docs/api-routes/introduction) instead of React pages.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js/) - your feedback and contributions are welcome!

## Deploy on Vercel

The easiest way to deploy your Next.js app is to use the [Vercel Platform](https://vercel.com/new?utm_medium=default-template&filter=next.js&utm_source=create-next-app&utm_campaign=create-next-app-readme) from the creators of Next.js.

Check out our [Next.js deployment documentation](https://nextjs.org/docs/deployment) for more details.


# Notice Board For Bootstrap 

- bootstrap 설치
    - [bootstrap 설치하기 - nodejs](https://medium.com/nextjs/how-to-add-bootstrap-in-next-js-de997371fd9c)

```shell

npm install bootstrap@next
or
yarn add bootstrap@next

```

# 적합합 폴더 구조 

- [추천 링크](https://dev.to/vadorequest/a-2021-guide-about-structuring-your-next-js-project-in-a-flexible-and-efficient-way-472)

```

/public
    favicon.ico
/src
    /common
        /components
            /elements
                /[Name]
                    [Name].tsx
                    [Name].test.ts
        /hooks
        /types
        /utils
    /modules
        /auth
            /api
                AuthAPI.js
                AuthAPI.test.js
            /components
                AuthForm.tsx
                AuthForm.test.ts
            auth.js
    /pages
        /api
          /authAPI
              authAPI.js
          /[Name]API
              [Name]API.js
        _app.tsx
        _document.tsx
        register.tsx

```

# API Routes 

```javascript

import cookies from '../../utils/cookies'

const handler = (req, res) => {
  // The cookie middleware will add the `set-cookie` header
  res.cookie('Next.js', 'api-middleware!')
  // Return the `set-cookie` header so we can display it in the browser and show that it works!
  res.end(res.getHeader('Set-Cookie'))
}

export default cookies(handler)

```

```javascript

import useSWR from 'swr'

const fetcher = (url) => fetch(url).then((res) => res.text())

export default function Index() {
  const { data, error } = useSWR('/api/cookies', fetcher)

  if (error) return <div>Failed to load</div>
  if (!data) return <div>Loading...</div>

  return <div>{`Cookie from response: "${data}"`}</div>
}

```

# Sample Projects


##### NextJS API Routes 

- https://nextjs.org/docs/api-routes/introduction

##### NextJS 찾다가 엉겹결에 찾았고 추후에 테스트 해볼 예정

- [NestJS](https://docs.nestjs.com/first-steps)

- [NodeJS NestJs Project](https://github.com/lujakob/nestjs-realworld-example-app)