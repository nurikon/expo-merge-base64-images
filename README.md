# expo-merge-base64-images

A native Expo module that merges two base64-encoded images into a single PNG image on Android.

## 📦 Installation

### For managed Expo projects

> ❗ This module requires a custom development client (i.e. not supported in Expo Go).

1. Install the package:

```bash
npx expo install expo-merge-base64-images
```

2. Run your app with a custom development client:

```bash
npx expo run:android
```

### For bare React Native projects

1. Install the package:

```bash
npm install expo-merge-base64-images
```

2. Install native dependencies:

```bash
npx pod-install
```

---

## 🔧 Android Configuration

No additional steps required if you’re using `expo run:android`.

---

## 📖 Usage

```ts
import mergeImages from 'expo-merge-base64-images';

const result = await mergeImages(base64_1, base64_2);
// result is a base64-encoded PNG image
```

You can then convert it to a file, use it in an `<Image>` component, or upload it.

---

## 📚 API

### `mergeBase64Images(base64_1: string, base64_2: string): Promise<string>`

Returns a base64-encoded PNG that combines the two input images vertically.

---

## 🤝 Contributing

Contributions are very welcome! Please refer to the [Expo contributing guide](https://github.com/expo/expo#contributing).
